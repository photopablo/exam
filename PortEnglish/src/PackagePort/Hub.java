// THOMAS PERRAULT
package PackagePort;

public class Hub {
    private Container[][] containers;

    static int ROWS = 10;
    static int COLUMNS = 12;

    public Hub() {
        containers = new Container[10][12];
        cleanPort(this.containers);
    }


    public Container[][] getContainers() {
        return containers;
    }

    public static void cleanPort(Container[][] port){
        for(int i = 0; i < COLUMNS; i++){
            for(int j = 0; j < ROWS; j++){
                port[j][i] = null;
            }
        }
    }
    public int count(int priority){
        int counter= 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 12; j++) {
                if (containers[i][j].getPriority() == priority){
                    counter++;
                }
            }
        }
        return counter;
    }



    public boolean stackContainer(Container container) {
        int priority = container.getPriority();
        for(int i = 0; i < COLUMNS; i++){
            for(int j = ROWS - 1; j >= 0; j--){
                if(containers[j][0] == null && priority == 1){
                    containers[j][0] = container;
                    return true;
                }
                if(containers[j][1] == null && priority == 2){
                    containers[j][1] = container;
                    return true;
                }
                if(containers[j][i] == null && i != 0 && i != 1 && priority == 3){
                    containers[j][i] = container;
                    return true;
                }
            }
        }
        return false;
    }


    public boolean removeContainer(int column) {
        boolean removed = false;
        for(int i = 0; i < ROWS; i++) {
            if (containers[i][column - 1] == null) {
                removed = false;
            } else {
                  containers[i][column -1] = null;
                removed = true;
                break;
            }
        }
        return removed;
    }



    public String findContainerById(int id, int mode) {
        String s = "No container found.";
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (containers[j][i] != null) {
                    if (containers[j][i].getId() == id) {
                        if (mode == 1) {
                            if(containers[j][i].getContentDescription() != null) {
                                s = containers[j][i].getContentDescription();
                                break;
                            } else {
                               break;
                            }
                        } else if (mode == 2) {
                            s = "Repeated ID.";
                        }
                    }
                }
            }
        }
        return s;
    }


    public String hubDisplay(){
        StringBuilder displayedHub = new StringBuilder();
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                if(containers[i][j] != null){
                    displayedHub.append(" O ");
                }else{
                    displayedHub.append(" V ");
                }
            }
            displayedHub.append("\n");
        }
        return displayedHub.toString();
    }


    public int countContainersByCountry(String country) {
        int count = 0;
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (containers[j][i] != null) {
                    if (containers[j][i].getCountryOfOrigin() == country) {
                            count++;
                        }
                    }
                }
            }
        return count;
    }
}