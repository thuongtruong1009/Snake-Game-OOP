package root;

import javax.swing.ImageIcon;

/**
 * @author https://www.github.com/thuongtruong1009/Snake-Game-OOP
 */
public class Root {
        // meta data
        public String author = "https://github.com/thuongtruong1009";
        public String version = "v8.0 (user setup)";
        public String date = "Wednesday, 3 November 2021";
        public String language = "JavaSwing";
        public String JDK = "14x";
        public String OS = "Window_NT x64 10.0";
        public String frameTitle = "Snake Game OOP";
        public int PANEL_WIDTH = 600;
        public int PANEL_HEIGHT = 600;

        // Image icons
        public ImageIcon fileMenu = new ImageIcon("./src/image/file2.png");
        public ImageIcon levelMenu = new ImageIcon("./src/image/level2.png");
        public ImageIcon moreMenu = new ImageIcon("./src/image/more2.png");
        public ImageIcon helpMenu = new ImageIcon("./src/image/help2.png");

        public ImageIcon icon = new ImageIcon("./src/image/snake_icon.png");

        public ImageIcon ic1 = new ImageIcon("./src/image/high_score.png");
        public ImageIcon ic2 = new ImageIcon("./src/image/new_game.png");
        public ImageIcon ic3 = new ImageIcon("./src/image/quit.png");

        public ImageIcon icn1 = new ImageIcon("./src/image/fork.png");
        public ImageIcon icn2 = new ImageIcon("./src/image/discuss2.png");
        public ImageIcon icn3 = new ImageIcon("./src/image/license.png");
        public ImageIcon icn4 = new ImageIcon("./src/image/contact.png");

        public ImageIcon Icn1 = new ImageIcon("./src/image/how_to_play.png");
        public ImageIcon Icn2 = new ImageIcon("./src/image/bug.png");
        public ImageIcon Icn3 = new ImageIcon("./src/image/love_icon.png");
        public ImageIcon Icn4 = new ImageIcon("./src/image/about.png");

        public ImageIcon download = new ImageIcon("./src/image/download.png");

        // Images Path In Array
        public String[] list = { "./src/backgrounds/background1.jpeg", "./src/backgrounds/background2.jpg",
                        "./src/backgrounds/background3.jpg", "./src/backgrounds/background4.jpg",
                        "./src/backgrounds/background5.jpg", "./src/backgrounds/background1.jpeg" };

        public String[] array2 = { "./src/image/easy.png", "./src/image/medium.png", "./src/image/difficult.png" };

        // buttons labels
        public String restartBtn = "./src/buttons/restartButton.png";
        public String pauseBtn = "./src/buttons/pauseButton.png";
        public String resumeBtn = "./src/buttons/resumeButton.png";
        public String quitBtn = "./src/buttons/quitButton.png";
        public String playBtn = "./src/buttons/playButton.png";
        public String newGameBtn = "./src/buttons/newGameButton.png";
        public String highScoreBtn = "./src/buttons/highScoreButton.png";
        public String loveBtn = "./src/buttons/loveButton.png";

        // music path
        public String introSound = "../musics/music.wav";
        public String eatAppleSound = "../musics/eat.wav";
        public String bombSound = "../musics/bomb.wav";
        public String winSound = "../musics/win.wav";
        public String failSound = "../musics/fail.wav";
        public String outroSound = "../musics/outro.wav";

        // link to broswer
        public String contact = "mailto:ititiu19228@student.hcmiu.edu.vn";
        public String discuss = "https://github.com/thuongtruong1009/Snake-Game-OOP/discussions";
        public String fork = "https://github.com/thuongtruong1009/Snake-Game-OOP/fork";
        public String bug = "https://github.com/thuongtruong1009/Snake-Game-OOP/issues";
        public String how_to_play = "https://github.com/thuongtruong1009/Snake-Game-OOP/blob/main/README.md#-how-to-play-this-game";

        public String scorePath = "./src/files/dataScore.txt";
        public String scoreTitle = "history";

        public String licensePath = "./src/files/LICENSE.txt";
        public String licenseTitle = "ECL-2.0 LICENSE";

        public String paypalLink = "https://paypal.me/thuongtruong1009";
        public String momoLink = "https://nhantien.momo.vn/0917085937";
        public String kofiLink = "https://ko-fi.com/thuongtruong1009";
        public String donateTitle = "Confirm";
        public String donateMessage = "Thank you for your donation!\nPlease choose one of below payment methods";

        public String downloadDisc = "Your file has been downloaded successfully!\nCheck at C:/Users/PC/Downloads/";
        public String downloadTitle = "message";
        // URL must be absolute path
        // public String downloadURL = "https://www.w3.org/TR/PNG/iso_8859-1.txt";
        public String downloadURL = "src/files/dataScore.txt";
        public String downloadAddress = "C:\\Users\\PC\\Downloads\\your_score.txt";

        public String deleteDisc = "This action will delete all your score history";
        public String deleteTitle = "Warning";

        public String aboutTitle = "About";
        public String aboutDesc = "SNAKE-GAME-OOP\n" + "\nAuthor: " + author + "\nVersion: " + version + "\nDate: "
                        + date + "\nLanguage: " + language + "\nJDK: " + JDK + "\nOS: " + OS;
}