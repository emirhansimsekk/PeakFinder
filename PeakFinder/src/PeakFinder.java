/**
 * Odev 1 -- Peak Finder
 *
 * Bu kod verilen 2D ve 3D diziler içinde peak leri bulmaya yarar.
 *
 * @author Emirhan Simsek
 *
 * @class BIL 201
 *
 * @date 31.29.2021   23.49
 *
 */



import java.util.Random;

public class PeakFinder {

    /**
     * generate random 2D array
     */
    public int[][] random2dArray(int n, int m) {
        int[][] a = new int[n][];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = new int[m];
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = r.nextInt(20);
            }
        }
        return a;

    }

    public int[][][] random3dArray(int n, int m, int z) {
        int[][][] a = new int[n][m][z];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < z; k++) {
                    a[i][j][k] = r.nextInt(20);
                }
            }
        }
        return a;
    }

    public void print2d(int[][] a) {
        for (int[] i : a) {
            for (int item : i) {
                System.out.print(item + "\t ");
            }
            System.out.println("");
        }
    }

    /**
     * n = number of rows, m = number of columns rowOrcol -> eger 0 ise sadece
     * satirlari(n) bolecek eger 1 ise sadece sutunlari(m) bolecek eger 2 ise bir
     * iterasyonda satirlari, diger iterasyonda sutunlari bolecek (m ve n
     * degerlerinden buyuk olani bolebilirsiniz, bu m ve n degerleri arasaindaki
     * fark fazla oldugunda daha mantikli.)
     */
    public int[] peakFinder2D(int[][] a, int n, int m, int rowOrcol) {
        int[] indx = new int[2];
        int startR = 0, endR = n, startC = 0, endC = m;
        int mid = 0;
        if (rowOrcol == 0) {

            while ((startR >= 0 && endR <= n)) {
                mid = (startR + endR) / 2;
                int max = a[mid][startC];
                int imax = 0;
                for (int i = startC; i < endC; i++) {
                    if (max <= a[mid][i]) {
                        max = a[mid][i];
                        imax = i;
                    }
                }

                if ((a[mid][imax] < a[mid + 1][imax])&& (a[mid + 1][imax] < 10) && (mid<n) ) {
                    startR = mid + 1;
                } else if ((a[mid][imax] < a[mid - 1][imax]) && (a[mid - 1][imax] > 0) && (mid>0)) {
                    endR = mid - 1;
                } else {
                    indx[0] = mid;
                    indx[1] = imax;
                    return indx;
                }
            }

            return indx;

        }

        else if (rowOrcol == 1) {

            while ((startC >= 0 && endC <= m)) {
                mid = (startC + endC) / 2;
                int max = a[startR][mid];
                int imax = 0;
                for (int i = startR; i < endR; i++) {
                    if (max <= a[i][mid]) {
                        max = a[i][mid];
                        imax = i;
                    }
                }

                if ((a[imax][mid] < a[imax][mid + 1]) && (a[imax][mid + 1] < 10) && (mid<m)) {
                    startC = mid + 1;
                } else if ((a[imax][mid] < a[imax][mid - 1]) && (a[imax][mid - 1] > 0) && (mid>0)) {
                    endC = mid - 1;
                } else {
                    indx[0] = imax;
                    indx[1] = mid;
                    return indx;
                }
            }

            return indx;
        }

        else if (rowOrcol == 2) {
            int count = 0;
            while ((startR >= 0 && endR <= n) && (startC >= 0 && endC <= m)) {
                if (count % 2 == 0) {
                    count++;
                    mid = (startR + endR) / 2;
                    int max = a[mid][startC];
                    int imax = 0;
                    for (int i = startC; i < endC; i++) {
                        if (max < a[mid][i]) {
                            max = a[mid][i];
                            imax = i;
                        }
                    }
                    if ((a[mid][imax] < a[mid + 1][imax]) && (a[mid + 1][imax] < 10) && (mid<n)) {
                        startR = mid + 1;
                    } else if ((a[mid][imax] < a[mid - 1][imax]) && (a[mid - 1][imax] > 0) && (mid>0)) {
                        endR = mid - 1;
                    } else {
                        indx[0] = mid;
                        indx[1] = imax;
                        return indx;
                    }
                }

                else if (count % 2 == 1) {
                    count++;
                    mid = (startC + endC) / 2;
                    int max = a[startR][mid];
                    int imax = 0;

                    for (int i = startR; i < endR; i++) {
                        if (max < a[i][mid]) {
                            max = a[i][mid];
                            imax = i;
                        }
                    }
                    if ((a[imax][mid] < a[imax][mid + 1]) && (a[imax][mid + 1] < 10) && (mid<m)) {
                        startC = mid + 1;
                    } else if ((a[imax][mid] < a[imax][mid - 1]) && (a[imax][mid - 1] > 0) && (mid>0)) {
                        endC = mid - 1;
                    } else {
                        indx[0] = imax;
                        indx[1] = mid;
                        return indx;
                    }
                }
            }
            return indx;
        }
        return indx;
    }

    /**
     * uc boyutlu arrayde peak bulan method
     *
     * @param a
     * @param r
     * @param c
     * @param d
     * @return
     */
    public int[] peakFinder3d(int[][][] a, int r, int c, int d) {
        // TODO verilen a'daki peaki bulup return etmesi gerekiyor
        int rowOrCol=0;
        int[] indx = new int[3];
        int[][] yedek = new int[r][c];

        int startD = 0, endD = d;
        int mid = 0;

        for (int i = 0; i < r; i++) {
            yedek[i] = new int[r];
            for (int j = 0; j < c; j++) {
                yedek[i][j] = a[i][j][mid];
            }
        }

        while ((startD >= 0 && endD <= d)) {
            mid = (startD + endD) / 2;

            int[] yedekPeak = peakFinder2D(yedek, r, c, rowOrCol);
            int e1 = yedekPeak[0], e2 = yedekPeak[1];

            if ((a[e1][e2][mid] < a[e1][e2][mid + 1])
                    && (a[e1][e2][mid + 1] < 10)&&(mid<10)) {

                startD = mid + 1;
            }

            else if ((a[e1][e2][mid] < a[e1][e2][mid - 1])
                    && (a[e1][e2][mid - 1] > 0)&&(mid>0)) {

                endD = mid - 1;

            }

            else {
                indx[0] = e1;
                indx[1] = e2;
                indx[2] = mid;
                return indx;
            }
        }

        return indx;
    }

    public static void main(String[] args) {
        PeakFinder pf = new PeakFinder();
        int n = 100, m = 10, z = 10, rowOrcol = 2;
        int[][] a = pf.random2dArray(n, m);
        int[][][] a3d = pf.random3dArray(n, m, z);
        /*
         * int[][]a={ {13,11,16,13,16,18,5,1,19,8}, {19,18,0,2,4,10,0,18,16,3},
         * {4,13,18,2,13,11,13,11,14,8}, {9,9,3,8,2,18,17,15,10,6},
         * {16,6,10,1,6,8,13,7,5,17}, {13,16,17,18,8,13,6,1,12,15},
         * {13,19,19,19,9,7,7,10,15,0}, {7,14,18,3,1,14,2,15,16,9},
         * {15,9,8,17,5,16,18,14,10,16}, {19,18,11,6,3,15,17,8,0,3} };
         */
        long t1 = System.nanoTime();
        pf.peakFinder2D(a, n, m, rowOrcol);
        // pf.peakFinder3d(a3d, n, m, z, rowOrcol);
        long t2 = System.nanoTime();

        System.out.printf("%d, %d, %d, %d\n", n, m, rowOrcol, t2 - t1);
        // int[] peak = pf.peakFinder2D(a, n, m, rowOrcol);
        // TODO: is peak correct?
        //int[] peak3d = pf.peakFinder3d(a3d, n, m, z, rowOrcol);
        // pf.print2d(a);
        //System.out.println("a3d[][][]: " + peak3d[0] + " " + peak3d[1] + " " + peak3d[2]);
        //System.out.println("max: " + a3d[peak3d[0]][peak3d[1]][peak3d[2]]);
    }
}