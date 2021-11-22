package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseBAgedBrieTest {

	public static final int NOT_EXPIRED_SELL_IN = 4;
	public static final int DEFAULT_QUALITY = 3;
	public static final String AGED_BRIE = "Aged Brie";
	public static final int EXPIRED_SELL_IN = -1;
	public static final int MAX_QUALITY = 50;

	@Test
	public void unexpiredAgedBrie_qualityIncreaseBy1() {
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(AGED_BRIE, NOT_EXPIRED_SELL_IN - 1, DEFAULT_QUALITY + 1);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void expiredAgedBrie_qualityIncreaseBy2() {
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, EXPIRED_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(AGED_BRIE, EXPIRED_SELL_IN - 1, DEFAULT_QUALITY + 2);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void unexpiredAgedBrie_qualityDoesNotGoBeyondMaximum() {
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, NOT_EXPIRED_SELL_IN, MAX_QUALITY);

		app.updateQuality();

		Item expected = new Item(AGED_BRIE, NOT_EXPIRED_SELL_IN - 1, MAX_QUALITY);
		assertItem(expected, app.items[0]);
	}

	private void assertItem(Item expected, Item actual) {
		assertEquals(expected.name, actual.name);
		assertEquals(expected.sellIn, actual.sellIn);
		assertEquals(expected.quality, actual.quality);
	}

	private GildedRose createGildedRoseWithOneItem(String name, int sellIn, int quality) {
		Item item = new Item(name, sellIn, quality);
		Item[] items = new Item[]{item};
		return new GildedRose(items);
	}
}
