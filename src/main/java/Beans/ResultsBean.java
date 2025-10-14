package Beans;

import Data.*;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultsBean implements Serializable {
    private ArrayList<MusicBand> result;

    public ResultsBean(){
        result = new ArrayList<MusicBand>();
    }
    public ArrayList<MusicBand> getResult() {
        return result;
    }

    public void setResult(ArrayList<MusicBand> result) {
        this.result = result;
    }

    public void addMusicBandToResult(MusicBand musicBand){
        result.add(musicBand);
    }
}
