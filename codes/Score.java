public class Score {
    private int score;          // 현재 점수
    private long startTime;     // 게임 시작 시간

    public Score() {
        this.score = 0;
        this.startTime = System.currentTimeMillis();
    }

    // 점수 가져오기
    public int getScore() {
        return score;
    }

    // 점수 증가 (경과 시간에 따른 점수 보너스 포함)
    public void increaseScore(int basePoints) {
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // 경과 시간(초)
        int bonus = Math.max(0, 100 - (int) elapsedTime); // 시간이 지남에 따라 보너스 감소
        this.score += basePoints + bonus;
    }

    // 현재 경과 시간 가져오기
    public long getElapsedTime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

    // 점수 리셋
    public void resetScore() {
        this.score = 0;
        this.startTime = System.currentTimeMillis();
    }
}
