package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.function.BooleanSupplier;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import container.GamePanel;

class testPanel {
	public testPanel() {
	}

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@BeforeClass
    public static void setUpClass() {
        System.out.println("Performing the testing for class: GamePanel");
        System.out.println(new String(new char[80]).replace('\0', '='));
    }
	
//	@Test
//    public void testSize() {
//        System.out.println("testing ... size()");
//        List<String> list = new SLinkedList<>();
//        list.add("0");
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("0");
//        list.add("1");
//        list.remove(1);
//        list.add("4");
//        assertEquals(6, list.size());
//    }

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	static void assertTrue(BooleanSupplier booleanSupplier) {
		Assert.assertTrue((String) null, booleanSupplier.getAsBoolean());
	}

	@Test
	void GamePanelTest() {
		GamePanel gamePanel = new GamePanel(100);
		assertTrue(() -> gamePanel.getWidth() == 600);
		assertTrue(() -> gamePanel.getHeight() == 600);
	}

	@Test
	void getWidthTest() {
		GamePanel gamePanel = new GamePanel(100);
		assertTrue(() -> gamePanel.getWidth() == 600);
	}
	@Test
	void getHeightTest() {
		GamePanel gamePanel = new GamePanel(100);
		assertTrue(() -> gamePanel.getHeight() == 600);
	}

	@Test
	void drawTest() throws IOException {
		GamePanel gamePanel = new GamePanel(100);
		gamePanel.drawSnake(null);
	}

	@Test
	void updateTest() {
		GamePanel gamePanel = new GamePanel(100);
		gamePanel.update(null);
	}

	@TestTemplate
	void getNameTest(String name) {
		GamePanel gamePanel = new GamePanel(100);
		gamePanel.getName();
	}
	@Test
	void startGameTest() {
		GamePanel gamePanel = new GamePanel(100);
		gamePanel.startGame();
	}
	@Test
	void newAppleTest() {
		GamePanel gamePanel = new GamePanel(100);
		gamePanel.newApple();
	}
	@Test
	void checkAppleTest() {
		GamePanel gamePanel = new GamePanel(100);
		gamePanel.checkApple();
	}

	@Test
	void getAppleTest() {
		GamePanel gamePanel = new GamePanel(100);
		gamePanel.getSize();
	}
	@Test
	void checkCollisions() {
		GamePanel gamePanel = new GamePanel(100);
		gamePanel.checkCollisions();
	}
	@Test
	void gameOver() throws IOException {
		GamePanel gamePanel = new GamePanel(100);
		gamePanel.gameOverView(null);
	}
	@Test
	void clickLabelImage() {
		GamePanel gamePanel = new GamePanel(100);
		gamePanel.clickLabelImage(null, 0, 0);
	}
	@Test
	void actionPerformed() {
		GamePanel gamePanel = new GamePanel(100);
		gamePanel.actionPerformed(null);
	}
}
