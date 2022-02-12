public class Arithmetic {
    private int var1 = 0;
    private int var2 = 0;

    public Arithmetic(int var1, int var2){
        this.var1 = var1;
        this.var2 = var2;
    }

    public int sum(){ return var1 + var2; }
    public int multiply () { return var1 * var2; }
    public int max() { return (var1 > var2) ? var1 : var2; }
    public int min() { return  (var1 < var2) ? var1 : var2; }
}
