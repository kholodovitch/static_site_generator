package com.kholodovitch.static_site_generator.data.global.settings;

public class Sitemap {
	private boolean generate = true;

	public boolean isGenerate() {
		return generate;
	}

	public void setGenerate(boolean generate) {
		this.generate = generate;
	}
}
