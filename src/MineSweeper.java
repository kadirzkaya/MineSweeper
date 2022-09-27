import java.util.Scanner;

public class MineSweeper {
    public static void main(String[] args) {
        int col, row, mineNum,tableRow,tableCol;
        String[][] mineArr,gameTable;
        boolean isBomb=true;

        Scanner sc=new Scanner(System.in);

        System.out.print("Mayın tarlasının satır sayısını giriniz : ");
        row=sc.nextInt();
        System.out.print("Mayın tarlasının sütun sayısını giriniz : ");
        col=sc.nextInt();
        mineArr=new String[row][col];
        gameTable=new String[row][col];

        mineNum=(row*col)/4;

        minePut(row,col,mineNum,mineArr);
        cellNumPut(mineArr);
        System.out.println("Mayınların Konumu");
        printMineSweeper(mineArr);
        System.out.println("=======================");
        System.out.println("Mayın tarlası oyununa hoşgeldiniz !");
        printMineSweeper(gameTable);

        while (isBomb){
            System.out.print("Satır giriniz : ");
            tableRow=sc.nextInt();
            System.out.print("Sütun giriniz : ");
            tableCol=sc.nextInt();
            if (tableRow<row && tableCol<col){
                isBomb=isMineExp(mineArr,gameTable,tableRow,tableCol);
                mineNum++;
                if (!isBomb){
                    System.out.println("Game Over!!");
                    break;
                }
                if (mineNum==row*col){
                    System.out.println("Oyunu Kazandınız !");
                    printMineSweeperNum(gameTable);
                    break;
                }
                System.out.println("=======================");

                printMineSweeperNum(gameTable);
            }else {
                System.out.println("Girdiğiniz koordinatlar oyun alanı dışındadır. Yeniden koordinat giriniz!!");
            }
        }
        System.out.println("=======================");

    }

    static void minePut(int row, int col, int mineNum, String[][] mineArr){
        int totalCell=row*col;
        while (mineNum>0){
            int mineMap=(int)(Math.random()*totalCell);
            int r=mineMap/col;
            int c=mineMap%col;
            if (mineArr[r][c].equals("*")){
                continue;
            }
            mineArr[r][c]="*";
            mineNum--;
        }
    }

    static void cellNumPut(String[][] mineArr){
        int cellNum=0;
        for (int i=0;i<mineArr.length;i++){
            for (int j=0;j<mineArr[i].length;j++){
                if (mineArr[i][j]!="*"){
                    if ((j+1)<mineArr[i].length && mineArr[i][j+1]=="*"){
                        cellNum++;
                    }
                    if ((j-1)>=0 && mineArr[i][j-1]=="*"){
                        cellNum++;
                    }
                    if ((i+1<mineArr.length) && (j-1)>=0 && mineArr[i+1][j-1]=="*"){
                        cellNum++;
                    }
                    if ((i+1<mineArr.length) && (j+1)<mineArr[i].length && mineArr[i+1][j+1]=="*"){
                        cellNum++;
                    }
                    if ((i+1<mineArr.length) && mineArr[i+1][j]=="*"){
                        cellNum++;
                    }
                    if ((i-1>=0) && mineArr[i-1][j]=="*"){
                        cellNum++;
                    }
                    if ((i-1>=0) && (j-1)>=0 && mineArr[i-1][j-1]=="*"){
                        cellNum++;
                    }
                    if ((i-1>=0) && (j+1)<mineArr[i].length && mineArr[i-1][j+1]=="*"){
                        cellNum++;
                    }
                    mineArr[i][j]=cellNum+"";
                    cellNum=0;
                }
            }
        }
    }

    static void printMineSweeper(String[][] mineArr){
        for (String[] ar: mineArr){
            for (String a:ar){
                if (a!="*"){
                    System.out.print("- ");
                }else
                    System.out.print(a+" ");
            }
            System.out.println();
        }
    }

    static void printMineSweeperNum(String[][] mineArr){
        for (String[] ar: mineArr){
            for (String a:ar){
                if (a==null){
                    System.out.print("- ");
                }else
                    System.out.print(a+" ");
            }
            System.out.println();
        }
    }

    static boolean isMineExp(String[][] mineArr, String[][] gameTable, int row, int col){
        if (mineArr[row][col]=="*"){
            return false;
        }else {
            gameTable[row][col]=mineArr[row][col];
        }
        return true;
    }
}
