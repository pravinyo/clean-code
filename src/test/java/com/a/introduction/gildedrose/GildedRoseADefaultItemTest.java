package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseADefaultItemTest {
	public static final String DEFAULT_ITEM = "DEFAULT_ITEM";
	public static final int EXPIRED_SELL_IN = -1;
	public static final int NOT_EXPIRED_SELL_IN = 15;
	public static final int DEFAULT_QUALITY = 3;

	private GildedRose createGildedRoseWithItem(Item item) {
		Item[] items = new Item[]{item};
		return new GildedRose(items);
	}

	private void assertItem(Item expected, Item actual) {
		assertEquals(expected.name, actual.name);
		assertEquals(expected.sellIn, actual.sellIn);
		assertEquals(expected.quality, actual.quality);
	}

	@Test
	public void unexpiredDefaultItem_qualityDecreasesBy1_SellInBy1() {
		Item item = new Item(DEFAULT_ITEM, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);
		GildedRose app = createGildedRoseWithItem(item);

		app.updateQuality();

		Item expected = new Item(DEFAULT_ITEM, NOT_EXPIRED_SELL_IN - 1, DEFAULT_QUALITY - 1);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void expiredDefaultItem_qualityDecreaseBy2_SellInBy1() {
		Item item = new Item(DEFAULT_ITEM, EXPIRED_SELL_IN, DEFAULT_QUALITY);
		GildedRose app = createGildedRoseWithItem(item);

		app.updateQuality();

		Item expected = new Item(DEFAULT_ITEM, EXPIRED_SELL_IN - 1, DEFAULT_QUALITY - 2);
		assertItem(expected, app.items[0]);
	}
}