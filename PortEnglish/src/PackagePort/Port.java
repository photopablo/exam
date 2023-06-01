
package PackagePort;

public class Port {
    public Hub[] hubs = new Hub[3];

    public Port(Hub firstHub, Hub secondHub, Hub thirdHub) {
        hubs[0] = firstHub;
        hubs[1] = secondHub;
        hubs[2] = thirdHub;

    }

    public int contar (int priority){
        int counter = 0;
        for (int i = 0; i < 3; i++) {
           counter += hubs[i].count(priority);
        }
        return counter;
    }
    public void checkAvailable(Container container){
        if(hubs[0].stackContainer(container) == false){
            if(hubs[1].stackContainer(container) == false){
                if(hubs[2].stackContainer(container) == false){
                    System.out.println("The level " + container.getPriority() + " is full.");
                }
            }
        }
    }
}