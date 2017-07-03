package br.ufes.dcel.movieTheather.model;

public enum TypeTech 
{
	BIDIMENSIONAL("2D"), TRIDIMENTIONAL("3D"), QUATROK("4K"), OITOK("8K");
	private String description;
	
	TypeTech ( String description )
	{
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
