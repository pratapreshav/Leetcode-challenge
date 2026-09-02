class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] index = new int[m][n];
        int startX = 0, startY = 0;
        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startX = i;
                    startY = j;
                } else if (ch == 'L') {
                    index[i][j] = litterCount++;
                }
            }
        }

        if (litterCount == 0) return 0;

        int totalMasks = 1 << litterCount;

        boolean[][][][] visited =
            new boolean[m][n][energy + 1][totalMasks];

        Queue<int[]> queue = new LinkedList<>();

        int initialMask = totalMasks - 1;

        queue.offer(new int[]{startX, startY, energy, initialMask});
        visited[startX][startY][energy][initialMask] = true;

        int moves = 0;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int s = 0; s < size; s++) {
                int[] state = queue.poll();

                int x = state[0];
                int y = state[1];
                int currEnergy = state[2];
                int mask = state[3];

                if (mask == 0) return moves;
                if (currEnergy == 0) continue;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                        continue;

                    if (classroom[nx].charAt(ny) == 'X')
                        continue;

                    char cell = classroom[nx].charAt(ny);

                    int newEnergy = cell == 'R'
                        ? energy
                        : currEnergy - 1;

                    int newMask = mask;

                    if (cell == 'L') {
                        int bit = index[nx][ny];
                        newMask &= ~(1 << bit);
                    }

                    if (!visited[nx][ny][newEnergy][newMask]) {
                        visited[nx][ny][newEnergy][newMask] = true;

                        queue.offer(new int[]{
                            nx, ny, newEnergy, newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}