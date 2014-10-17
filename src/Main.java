import java.util.*;
import java.io.*;

public class Main {
    FastScanner in;
    PrintWriter out;
    int[] head;
    int[] terminal;
    int[] next;
    int n;
    int m;
    int k = 0;
    boolean[] used;
    int[] dynamic;
    int[] queue;
    int pointerend = 0;
    int pointerbeg = 1;

    public void solve() throws IOException {
        used[0] = true;
        Arrays.fill(queue, -1);
        queue[0] = 0;
        while(queue[pointerend]!=-1) {
            int vertex = queue[pointerend];
            queue[pointerend] = -1;
            pointerend++;

            int i = head[vertex];
            while (i!=0) {
                int u = terminal[i];
                if (!used[u]) {
                    used[u] = true;
                    queue[pointerbeg] = u;
                    pointerbeg++;
                    dynamic[u]=dynamic[vertex]+1;
                }
                i = next[i];
            }
        }
    }

    public void run() {
        try {
            in = new FastScanner(new File("pathbge1.in"));
            out = new PrintWriter(new File("pathbge1.out"));
            n = in.nextInt();
            m = in.nextInt();
            head = new int[2*n+1];
            terminal = new int[2*m+1];
            next = new int[2*m+1];
            used = new boolean[n];
            dynamic = new int[n];
            queue = new int[1000000];
            for (int i = 0; i < m ; i++) {
                int begin = in.nextInt();
                int end = in.nextInt();
                k++;
                terminal[k] = end-1;
                next[k] = head[begin-1];
                head[begin-1] = k;
                k++;
                terminal[k] = begin-1;
                next[k] = head[end-1];
                head[end-1] = k;
            }
           solve();
            for (int i = 0; i<n; i++) {
                out.print(dynamic[i]+" ");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] arg) {
        new Main().run();
    }
}
