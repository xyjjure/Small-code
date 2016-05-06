package xyj;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Tetris extends JFrame {
 
    public Tetris() {

    }

    public static <JavaClip> void main(String[] args) {
        final Tetrisblok a = new Tetrisblok();
        Tetris frame = new Tetris();
        frame.addKeyListener(a);
        frame.add(a);
        JMenuBar menu=new JMenuBar();
        frame.setJMenuBar(menu);
        JMenu game = new JMenu("��Ϸ");
        JMenuItem newgame = game.add("����Ϸ");
        JMenuItem start = game.add("��ʼ");
        JMenuItem pause = game.add("��ͣ");
        JMenuItem goon = game.add("����");
        JMenuItem exit = game.add("�˳�");
        JMenu help = new JMenu("����");
        JMenuItem about = help.add("����");
        menu.add(game);
        menu.add(help);
        //frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(440, 500);
        frame.setTitle("Tetris�ڲ��");
        // frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setResizable(false);
        newgame.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		a.newblock();
                a.newmap();
                a.drawwall();
        	}
        });
        start.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
                a.startTimer();
        	}
        });
        pause.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
               a.stopTimer();
        	}
        });
        goon.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
               a.startTimer();
        	}
        });
        exit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
             System.exit(0);
        	}
        });
        about.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(null, "�����ߣ� ����� ");
        	}
        });
    }
}

// ����һ������˹������
class Tetrisblok extends JPanel implements KeyListener {
    // blockType ����������
    // turnState������״̬
	Timer timer = new Timer(500, new TimerListener());
    private int blockType=-1;
    private int score = 0;
    private int turnState=-1;
    private int nextblockType;
    private int nextturnState;
    private int x;

    private int y;

    private int i = 0;

    int j = 0;
    int flag = 0;
    // �����Ѿ����µķ���x=0-11,y=0-21;
    int[][] map = new int[13][23];

    // �������״��һ�������������S��Z��L��J��I��O��T 7�� �ڶ��� ������ת���� ��������Ϊ �������
    private final int shapes[][][] = new int[][][] {
    // i
            {       { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 },
                    { 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0 } },
            // s
            {       { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 } },
            // z
            {       { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 } },
            // j
            {       { 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                    { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
            // o
            {       { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
            // l
            {       { 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
            // t
            {       { 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
                    { 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 } } };

    // �����·���ķ���
    
    public void newblock(){
    	if((blockType==-1)&&(turnState==-1)){
    		blockType=(int)(Math.random()*1000)%7;
    		turnState=(int)(Math.random()*1000)%4;
    		nextblockType=(int)(Math.random()*1000)%7;
            nextturnState=(int)(Math.random()*1000)%4;
    	}
    	else {
        blockType=nextblockType;
        turnState=nextturnState;
        nextblockType=(int)(Math.random()*1000)%7;
        nextturnState=(int)(Math.random()*1000)%4;
    	}
        x = 4;
        y = 0;
        if (gameover(x, y) == 1) {
        	timer.stop();
            JOptionPane.showMessageDialog(null, "GAME OVER");
            newmap();
            drawwall();
            score = 0;
            File wavFile = new File("E:\\Eclipse\\xyj\\src\\music\\L_MBP3.wav");
        	try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(wavFile);
				  Clip clip = null;
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	        	  try {
					clip.open(ais);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	  clip.start();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    } 

    // ��Χǽ
    public void drawwall() {
        for (i = 0; i < 12; i++) {
            map[i][21] = 2;
        }
        for (j = 0; j < 22; j++) {
            map[11][j] = 2;
            map[0][j] = 2;
        }
    }

    // ��ʼ����ͼ
    public void newmap() {
        for (i = 0; i < 12; i++) {
            for (j = 0; j < 22; j++) {
                map[i][j] = 0;
            }
        }
    }

    // ��ʼ�����췽��
    Tetrisblok() {
    	newblock();
        newmap();
        drawwall();
    }

    // ��ת�ķ���
    public void turn() {
        int tempturnState = turnState;
        turnState = (turnState + 1) % 4;
        if (blow(x, y, blockType, turnState) == 1) {
        }
        if (blow(x, y, blockType, turnState) == 0) {
            turnState = tempturnState;
        }
        repaint();
    }

    // ���Ƶķ���
    public void left() {
        if (blow(x - 1, y, blockType, turnState) == 1) {
            x = x - 1;
        }
        ;
        repaint();
    }

    // ���Ƶķ���
    public void right() {
        if (blow(x + 1, y, blockType, turnState) == 1) {
            x = x + 1;
        }
        ;
        repaint();
    }

    // ����ķ���
    public void down() {
        if (blow(x, y + 1, blockType, turnState) == 1) {
            y = y + 1;
            delline();
        }
        ;
        if (blow(x, y + 1, blockType, turnState) == 0) {
            add(x, y, blockType, turnState);
         
            newblock();
            delline();
        }
        ;
        repaint();
    }

    // �Ƿ�Ϸ��ķ���
    public int blow(int x, int y, int blockType, int turnState) {
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                if (((shapes[blockType][turnState][a * 4 + b] == 1) && (map[x
                        + b + 1][y + a] == 1))
                        || ((shapes[blockType][turnState][a * 4 + b] == 1) && (map[x
                                + b + 1][y + a] == 2))) {

                    return 0;
                }
            }
        }
        return 1;
    }

    // ���еķ���
    public void delline() {
        int c = 0;
        for (int b = 0; b < 22; b++) {
            for (int a = 0; a < 12; a++) {
                if (map[a][b] == 1) {

                    c = c + 1;
                    if (c == 10) {
                        score += 10;
                        for (int d = b; d > 0; d--) {
                            for (int e = 0; e < 11; e++) {
                                map[e][d] = map[e][d - 1];

                            }
                        }
                    }
                }
            }
            c = 0;
        }
    }

    // �ж���ҵķ���
    public int gameover(int x, int y) {
        if (blow(x, y, blockType, turnState) == 0) {
            return 1;
        }
        return 0;
    }

    // �ѵ�ǰ���map
    public void add(int x, int y, int blockType, int turnState) {
        int j = 0;
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                if (map[x + b + 1][y + a] == 0) {
                    map[x + b + 1][y + a] = shapes[blockType][turnState][j];
                }
                ;
                j++;
            }
        }
    }

    // ������ĵķ���
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // ����ǰ����
        for (j = 0; j < 16; j++) {
            if (shapes[blockType][turnState][j] == 1) {
            	g.setColor(Color.red);
                g.fillRect((j % 4 + x + 1) * 20, (j / 4 + y) * 20, 20, 20);
            }
        }
        // ���Ѿ��̶��ķ���
        for (j = 0; j < 22; j++) {
            for (i = 0; i < 12; i++) {
                if (map[i][j] == 1) {
                	g.setColor(Color.blue);
                    g.fillRect(i * 20, j * 20, 20, 20);

                }
                if (map[i][j] == 2) {
                	g.setColor(Color.black);
                    g.drawRect(i * 20, j * 20, 20, 20);

                }
            }
        }
        g.setFont(new Font("Courier",Font.BOLD,20));
        g.drawString("score=" + score, 250, 50);
        g.setFont(new Font("����",Font.PLAIN,20));
        g.drawString("���Ʋ�����Ϸ��", 250, 90);
        g.drawString("�ܾ�������Ϸ��", 250, 110);
        g.drawString("ע�����ұ�����", 250, 130);
        g.drawString("������ƭ�ϵ���", 250, 150);
        g.drawString("�ʶ���Ϸ���ԣ�", 250, 170);
        g.drawString("������Ϸ����", 250, 190);
        g.drawString("������ʱ�䣬", 250, 210);
        g.drawString("���ܽ������", 250, 230);
        g.setColor(Color.black);
        g.drawRect(255, 255, 150,150);
        g.drawString("��һ������", 260,280);
        for (j = 0; j < 16; j++) {
            if (shapes[nextblockType][nextturnState][j] == 1) {
            	g.setColor(Color.black);
                g.fillRect((j % 4 + 15) * 20, (j / 4 + 16) * 20, 20, 20);
            }
        }
    }
    // ���̼���
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_DOWN:{
        	File wavFile = new File("E:\\Eclipse\\xyj\\src\\music\\Button11.wav");
        	try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(wavFile);
				  Clip clip = null;
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	        	  try {
					clip.open(ais);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	  clip.start();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            down();
        }
            break;
        case KeyEvent.VK_UP:{
        	File wavFile = new File("E:\\Eclipse\\xyj\\src\\music\\Button18.wav");
        	try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(wavFile);
				  Clip clip = null;
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	        	  try {
					clip.open(ais);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	  clip.start();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            turn();
        }
            break;
        case KeyEvent.VK_RIGHT:{
        	File wavFile = new File("E:\\Eclipse\\xyj\\src\\music\\Button11.wav");
        	try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(wavFile);
				  Clip clip = null;
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	        	  try {
					clip.open(ais);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	  clip.start();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            right();
        }
            break;
        case KeyEvent.VK_LEFT:{
        	File wavFile = new File("E:\\Eclipse\\xyj\\src\\music\\Button11.wav");
        	try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(wavFile);
				  Clip clip = null;
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	        	  try {
					clip.open(ais);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	  clip.start();
			} catch (UnsupportedAudioFileException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            left();
        }
            break;
        }

    }
    public void startTimer(){
    	timer.start();
    }
    public void stopTimer(){
        timer.stop();
    }
    // ����
    public void keyReleased(KeyEvent e) {
    }

    // ����
    public void keyTyped(KeyEvent e) {
    }

    // ��ʱ������
    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            repaint();
            if (blow(x, y + 1, blockType, turnState) == 1) {
                y = y + 1;
                delline();
            }
            ;
            if (blow(x, y + 1, blockType, turnState) == 0) {

                if (flag == 1) {
                    add(x, y, blockType, turnState);
                    delline();                   
                    newblock();
                    flag = 0;
                }
                flag = 1;
            }
            ;
        }
    }
}