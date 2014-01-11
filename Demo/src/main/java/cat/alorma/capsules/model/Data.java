package cat.alorma.capsules.model;

import java.util.List;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;

/**
 * Created by Bernat on 25/11/13.
 */
public class Data {

    private String name;
    private List<Capsule> capsules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Capsule> getCapsules() {
        return capsules;
    }

    public void setCapsules(List<Capsule> capsules) {
        this.capsules = capsules;
    }
}
