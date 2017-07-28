package br.ufes.dcel.movieTheather.model;

	public enum Role { 
		ADMIN("ADMIN"),USER("USER");
		private String description;
		
		Role ( String description )
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


