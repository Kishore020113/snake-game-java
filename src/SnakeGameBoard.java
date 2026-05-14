import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SnakeGameBoard{
    private static Queue<Node> queue = new LinkedList<>();
    private static Queue<Node> food = new LinkedList<>();
    private char[][] board = null;
    static Scanner sc = new Scanner(System.in);

    public SnakeGameBoard(int row, int col){
        board = new char[row][col];
        board[0][0] = '.';
        queue.add(new Node(0,0));
        food.add(new Node(0,3));
        food.add(new Node(2,1));
        food.add(new Node(4,4));
        food.add(new Node(1,5));
        food.add(new Node(5,0));
        food.add(new Node(3,2));
        food.add(new Node(5,3));
        Node node = food.poll();
        board[node.getRow()][node.getCol()] = 'X';
    }
    public void initalize(int row, int col){
        if(row >= 0 && row < board.length && col >= 0 && col < board[0].length){
            queue.add(new Node(row,col));
            char c = board[row][col];
            if(board[row][col] != 'X'){
                Node node = queue.poll();
                board[node.getRow()][node.getCol()] = '\0';
            }
            if(c == '.'){
                System.out.println("Game Over");
                System.exit(0);
            }
            else if(board[row][col] == 'X'){
                if(food.isEmpty()){
                    System.out.println("You Won");
                    System.exit(0);
                }
                Node node = food.poll();
                board[node.getRow()][node.getCol()] = 'X';
            }
            board[row][col] = '.';
            display();
            if(!queue.isEmpty()){
                System.out.print("Enter a position : ");
                char direction = sc.next().charAt(0);
                if(direction == 'u' || direction == 'U'){
                    initalize(--row,col);
                }
                else if(direction == 'd' || direction == 'D'){
                    initalize(++row,col);
                }
                else if(direction == 'r' || direction == 'R'){
                    initalize(row,++col);
                }
                else if(direction == 'l' || direction == 'L'){
                    initalize(row,--col);
                }
            }
        }else{
            System.out.println("Invaild Input");
            System.exit(0);
        }

    }
//    public void moveforward(int row, int col){
//        board[row][col] = '.';
//        display();
//    }
    public void display(){
        for(char [] a : board){
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
    }
}