public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Type type;
    protected String info;

    public Task(String description, boolean isDone, String info) {
        this.description = description.trim();
        this.isDone = isDone;
        this.info = info.trim();
    }

    public boolean isDone() {
        return isDone;
    }

    public String getSymbol() {
        switch (type){
            case TODO:
                return "T";
            case DEADLINE:
                return "D";
            case EVENT:
                return "E";
            default:
                return "";
        }
    }

    public Type getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public String getDescription() {
        return  description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (info.equals("")) {
            return "[" + getSymbol() + "][" + getStatusIcon() + "] " + description;
        } else {
            String[] infos = info.split(" ", 2);
            return "[" + getSymbol() + "][" + getStatusIcon() + "] " + description + " (" + infos[0] +":  " +  infos[1] + ")";
        }
    }

    public abstract String getFileStringFormat();
}