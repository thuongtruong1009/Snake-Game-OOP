package root;
/**
 * @author https://www.github.com/thuongtruong1009/Snake-Game-OOP
 */
public interface ISnake {
    void setHeight(int height);
    void setWidth(int width);
    void setSpeed (int speed);
    void setLevel (int level);
    void setHighScore (int highScore);
    void move();
    void eat();
    void die();
    void start();
    void stop();
    void pause();
    void resume();
    void restart();
    void gameOver();
    void checkCollisions();
    void checkApple();
    void newApple();
}
