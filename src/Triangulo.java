import java.awt.Color;
import java.awt.Graphics;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Triangulo extends JFrame{

    private final int CONSTANTE = 20;
    private final float MAXIMIZAR = 1.1f; 
    private JPanel panel;
    private int x1=250, y1 = 200, x2 = 300, y2=240;
    private boolean flag = false, up, left, right, down, max;
    public Triangulo(){
        super("Dibujar un triangulo");
        this.setSize(650,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        inicarComponentes();
    }
    public void inicarComponentes(){
        agregarPanel();
        
    } 
    public void agregarPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        this.add(panel);
        panel.setFocusable(true);
        eventosDelTeclado();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
            
        if(flag && up){
            // Borramos las anteriores coordenadas
            g.setColor(new Color(0,0,0));
            pintarTriangulo(g);

            // pintamos con las nuevas coordenadas
            g.setColor(Color.BLACK);
            y1 -= CONSTANTE;
            y2 -= CONSTANTE;

            pintarTriangulo(g);
            flag = false;
            up = false;
        }else if(flag && down){
            g.setColor(new Color(0,0,0));
            pintarTriangulo(g);

            // pintamos con las nuevas coordenadas
            g.setColor(Color.BLACK);
            y1 += CONSTANTE;
            y2 += CONSTANTE;

            pintarTriangulo(g);
            flag = false;
            down = false;
        }else if(flag && right){
            g.setColor(new Color(0,0,0));
            pintarTriangulo(g);

            // pintamos con las nuevas coordenadas
            g.setColor(Color.BLACK);
            x1 += CONSTANTE;
            x2 += CONSTANTE;
            pintarTriangulo(g);
            flag = false;
            right = false;

        }else if(flag && left){
            g.setColor(new Color(0,0,0));
            pintarTriangulo(g);

            // pintamos con las nuevas coordenadas
            g.setColor(Color.BLACK);
            x1 -= CONSTANTE;
            x2 -= CONSTANTE;
            pintarTriangulo(g);
            flag = false;
            left = false;
        }else if(flag && max){
            g.setColor(new Color(0,0,0));
            pintarTriangulo(g);

            // pintamos con las nuevas coordenadas
            g.setColor(Color.BLACK);
            flag = false;
            max = false;
        }

        g.setColor(Color.BLACK);
        pintarTriangulo(g);
        flag = false;
        
    }

    private void pintarTriangulo(Graphics g){

        
        // Pintamos la base
        algoritmoBresenham(x1, y1, x1, y1+61, g);

        // creamos el lado derecho del triangulo
        algoritmoBresenham(x1, y1, x2, y2, g);

        //creamos el lado izquierdo del triangulo
        algoritmoBresenham(x2, y2, x1, (y2+CONSTANTE), g);
    }
    
    private void algoritmoBresenham(int x1, int y1, int x2, int y2, Graphics g){

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;
        int e2;
        while (true) {
        g.drawRect(x1, y1, 1, 1);
        if (x1 == x2 && y1 == y2) {
            break;
        }
        e2 = 2 * err;
        if (e2 > -dy) {
            err = err - dy;
            x1 = x1 + sx;
        }
        if (e2 < dx) {
            err = err + dx;
            y1 = y1 + sy;
            }
        }
        repaint();
    }

    private void eventosDelTeclado(){
        KeyListener eventoTeclado = new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e){
                
            }
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    flag = true;
                    up = true;
                }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    flag = true;
                    down = true;
                }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    flag = true;
                    right = true;
                }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    flag = true;
                    left = true;
                }else if(e.getKeyCode() == KeyEvent.VK_M){
                    flag = true;
                    max = true;
                }
                
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
            }
            @Override
            public void keyReleased(KeyEvent e){
                
            }
        };
            
        panel.addKeyListener(eventoTeclado);    
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() { 
            @Override
            public void run() {
                try{
                    Triangulo frame = new Triangulo();
                    frame.setVisible(true);
                }catch (Exception e ){ e.printStackTrace();} }});}
 
}

