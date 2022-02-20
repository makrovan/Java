public class Computer {
    private final String vendor;
    private final String name;

    private Cpu cpu;
    private Memory memory;
    private Disk disk;
    private Screen screen;
    private Keyboard keyboard;

    //private static int computerWeight = calculateComputerWeight();

    /*private static int calculateComputerWeight(){
    }*/

    public int getComputerWeight() {
        return cpu.getWeight() +
                memory.getWeight() +
                disk.getWeight() +
                screen.getWeight() +
                keyboard.getWeight() ;
    }

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "vendor='" + vendor + '\'' +
                ", name='" + name + '\'' +
                ", cpu=" + cpu +
                ", memory=" + memory +
                ", disk=" + disk +
                ", screen=" + screen +
                ", keyboard=" + keyboard +
                '}';
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }
}
