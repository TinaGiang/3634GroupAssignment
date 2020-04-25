package au.edu.unsw.infs3634.Group79App;

class Items {

    private int level;
    private String name;
    private String scores;
    private int ScoreInt;

    private String rank;
    private int sort;

    public Items(int image, String text1, String text2, String text3, int text4) {
        level = image;
        name = text1;
        scores = text2;
        rank = text3;
        sort = text4;
    }

    public int getmImage() {
        return level;
    }

    public void setDrawableId (int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScores() {



        return scores;
    }

    public int getScoresInt() {

        int ScoreInt = Integer.parseInt(scores);
        return ScoreInt;
    }



    public int getSort() {


        return sort;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }



    public String getRank() {
        return rank;
    }

    public void setRank(String name) {
        this.rank = name;
    }



}
