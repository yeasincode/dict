package db.dict;
/**
 * <pre>
 * 
 *  BoShang Technology
 *  File: TokenModel.java
 * 
 *  BoShang, Inc.
 *  Copyright (C): 2019
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 * 	$Id: TokenModel.java 72642 2009-01-01 20:01:57Z BoShang\Administrator $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2019年1月2日		Administrator		Initial.
 *  
 * </pre>
 */
public class CharacterModel
{
	private String name;
	private String complexName;
	private String spellName;
	private String radicalName;
	private int length;
	private String wubi;
	private String definition;
	private int isMultipleSpell=0;

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSpellName()
	{
		return spellName;
	}
	public void setSpellName(String spellName)
	{
		this.spellName = spellName;
	}
	public String getRadicalName()
	{
		return radicalName;
	}
	public void setRadicalName(String radicalName)
	{
		this.radicalName = radicalName;
	}
	public int getLength()
	{
		return length;
	}
	public void setLength(int length)
	{
		this.length = length;
	}
	public String getWubi()
	{
		return wubi;
	}
	public void setWubi(String wubi)
	{
		this.wubi = wubi;
	}
	public String getDefinition()
	{
		return definition;
	}
	public void setDefinition(String definition)
	{
		this.definition = definition;
	}
	public String getComplexName()
	{
		return complexName;
	}
	public void setComplexName(String complexName)
	{
		this.complexName = complexName;
	}
	public int getIsMultipleSpell()
	{
		return isMultipleSpell;
	}
	public void setIsMultipleSpell(int isMultipleSpell)
	{
		this.isMultipleSpell = isMultipleSpell;
	}
}

/*
*$Log: av-env.bat,v $
*/