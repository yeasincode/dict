package db.dict;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main(String[] args)
	{
		String filePath = "D:\\360极速浏览器下载\\新华字典Excel版.xls";
		List<CharacterModel> list = new ArrayList<>();
		try
		{
			// 1、获取文件输入流
			InputStream inputStream = new FileInputStream(filePath);
			// 2、获取Excel工作簿对象
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			// 3、得到Excel工作表对象
			HSSFSheet sheetAt = workbook.getSheetAt(0);
			// 4、循环读取表格数据
			for (Row row : sheetAt)
			{
				// // 首行（即表头）不读取
				// if (row.getRowNum() == 0)
				// {
				// continue;
				// }

				// 读取当前行中单元格数据，索引从0开始
				int isMultipleSpell = 0;
				String spellName = row.getCell(1).getStringCellValue();
				String[] spellNames = spellName.split("、");
				if (spellNames.length > 1)
				{
					isMultipleSpell = 1;
				}

				String name = getResult(row, 0, "");
				String radicalName = getResult(row, 2, "");
				int length = (int) row.getCell(3).getNumericCellValue();
				String complexName = getResult(row, 4, name);
				String wubi = getResult(row, 5, "");
				String definition = getResult(row, 8, "");

				for (String spell : spellNames)
				{
					CharacterModel model = new CharacterModel();
					model.setSpellName(spell);
					model.setComplexName(complexName);
					model.setDefinition(definition);
					model.setLength(length);
					model.setName(name);
					model.setRadicalName(radicalName);
					model.setWubi(wubi);
					model.setIsMultipleSpell(isMultipleSpell);
					list.add(model);
				}

			}
			// 5、关闭流
			workbook.close();

			List<RadicalAnalysisModel> radicalAnalysisModels = calcRadicalAnalysis(list);
			List<SpellAnalysisModel> spellAnalysisModels = calcSpellAnalysis(list);
			spellAnalysisModels.sort(new Comparator<SpellAnalysisModel>()
			{
				@Override
				public int compare(SpellAnalysisModel o1, SpellAnalysisModel o2)
				{
					return o1.getSpellName().compareTo(o2.getSpellName());
				}
			});

			DataOutputStream dataOutputStream=new DataOutputStream(new FileOutputStream("result.txt"));
			for (SpellAnalysisModel item : spellAnalysisModels)
			{
				dataOutputStream.writeBytes(item.getSpellName()+":"+item.getCount());
				dataOutputStream.write(new byte[]{13,10});
				System.out.println("拼音:" + item.getSpellName() + "  总数：" + item.getCount());
			}
			dataOutputStream.close();


			System.out.println("==============================");
			for (RadicalAnalysisModel item : radicalAnalysisModels)
			{
				System.out.println("部首：" + item.getRadical() + "  总数：" + item.getCount());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static List<SpellAnalysisModel> calcSpellAnalysis(List<CharacterModel> list)
	{
		Map<String, SpellAnalysisModel> map = new HashMap<>();
		for (CharacterModel characterModel : list)
		{
			SpellAnalysisModel spellAnalysisModel = map.get(characterModel.getSpellName());
			if (spellAnalysisModel == null)
			{
				spellAnalysisModel = new SpellAnalysisModel();
				spellAnalysisModel.setSpellName(characterModel.getSpellName());
				spellAnalysisModel.setCount(0);
			}

			spellAnalysisModel.setCount(spellAnalysisModel.getCount() + 1);
			map.put(characterModel.getSpellName(), spellAnalysisModel);
		}

		return new ArrayList<>(map.values());
	}

	public static List<RadicalAnalysisModel> calcRadicalAnalysis(List<CharacterModel> list)
	{
		Map<String, RadicalAnalysisModel> map = new HashMap<>();
		for (CharacterModel characterModel : list)
		{
			RadicalAnalysisModel radicalAnalysisModel = map.get(characterModel.getRadicalName());
			if (radicalAnalysisModel == null)
			{
				radicalAnalysisModel = new RadicalAnalysisModel();
				radicalAnalysisModel.setRadical(characterModel.getRadicalName());
				radicalAnalysisModel.setCount(0);
			}

			radicalAnalysisModel.setCount(radicalAnalysisModel.getCount() + 1);
			map.put(characterModel.getRadicalName(), radicalAnalysisModel);
		}

		return new ArrayList<>(map.values());
	}

	public static String getResult(Row row, int index, String defaultResult)
	{
		Cell cell = row.getCell(index);

		if (cell == null || isEmpty(cell.getStringCellValue()))
		{
			return defaultResult;
		}
		return row.getCell(index).getStringCellValue();
	}

	public static boolean isEmpty(String aStr)
	{
		if (aStr == null || aStr.trim().isEmpty() || aStr.trim().equals("undefined"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private static void intoSqllite()
	{

	}
}
