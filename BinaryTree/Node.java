public class Node {
    //node class'i bir agacin dugumunu temsil eder
    //data dugumu tasidigi veri
    //leftChild sol alt dugum
    //rightChild sag alt dugum
    public long data;
    public Node leftChild;
    public Node rightChild;
    public void displayNode(){ //dugumun data'sini ekrana bastiracak metot
        System.out.println("{" + data +"}");
    }
}
