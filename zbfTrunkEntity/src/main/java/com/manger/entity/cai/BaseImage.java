package com.manger.entity.cai;

import com.manger.core.entity.IdEntity;

public class BaseImage extends IdEntity{
		/**
	 * 
	 */
	private static final long serialVersionUID = -2755418203173360392L;
		public Integer getVersion() {
			return version;
		}
		public void setVersion(int version) {
			this.version = version;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getVirtualUrl() {
			return virtualUrl;
		}
		public void setVirtualUrl(String virtualUrl) {
			this.virtualUrl = virtualUrl;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public Long getGoodsId() {
			return goodsId;
		}
		public void setGoodsId(Long goodsId) {
			this.goodsId = goodsId;
		}
		private int version;
		private String name;
		private String virtualUrl;
		private String url;
		private Long userId;
		private Long goodsId;
}
