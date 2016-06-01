package com.kholodovitch.static_site_generator.data.global;

import com.kholodovitch.static_site_generator.data.global.settings.Sitemap;

public class Settings {
	private Sitemap sitemap;

	public Sitemap getSitemap() {
		return sitemap;
	}

	public void setSitemap(Sitemap sitemap) {
		this.sitemap = sitemap;
	}
}
