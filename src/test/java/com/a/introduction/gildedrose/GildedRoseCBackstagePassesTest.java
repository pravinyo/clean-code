package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GildedRoseCBackstagePassesTest {

	public static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
	public static final int NOT_EXPIRED_SELL_IN = 15;
	public static final int EXPIRED_SELL_IN = -1;
	public static final int DEFAULT_QUALITY = 3;
	public static final int TEN_OR_LESS_SELL_IN = 7;
	public static final int FIVE_OR_LESS_SELL_IN = 4;

	@Test
	public void unexpiredBackStagePass_qualityIncreaseBy1() {
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,
				NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,
				NOT_EXPIRED_SELL_IN - 1, DEFAULT_QUALITY + 1);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void unexpiredBackStagePass_lessThan10SellIn_qualityIncreaseBy2() {
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,
				TEN_OR_LESS_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,
				TEN_OR_LESS_SELL_IN - 1, DEFAULT_QUALITY + 2);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void unexpiredBackStagePass_lessThan10SellIn_qualityIncreaseBy3() {
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,
				FIVE_OR_LESS_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,
				FIVE_OR_LESS_SELL_IN - 1, DEFAULT_QUALITY + 3);
		assertItem(expected, app.items[0]);
	}

	@Test
	public void expiredBackStagePass_qualityBecomeZero() {
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,
				EXPIRED_SELL_IN, DEFAULT_QUALITY);

		app.updateQuality();

		Item expected = new Item(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT,
				EXPIRED_SELL_IN - 1, 0);
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