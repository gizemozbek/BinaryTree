import java.util.Stack;
public class Tree {
    //tree sinifi ikili arama agacini temsil eden sinif
    // agacin koku root degiskenidir
    private Node root;

        public Tree(){
            root = null; //baslangicta agac bos
        }
    public void insert (long data){
            Node newNode = new Node();
            //veri ekleme
            newNode.data = data;

            //agac yani root bossa
        if (root == null){
            root = newNode; //agacin root'u yoksa yeni dugum dogrudan root olmalidir
        }
        //root doluysa
        else {
            //roottan baslanir
            Node current = root;
            Node parent;

            while(true){
                //parent ->su an dikkate alinan konum
                parent = current;
                // deger kokten kucuksa sol alt agacta
                if(data < current.data){
                //current dugumun sol alt agacina gecilir
                    current = current.leftChild;
                    //sol alt agac bos mu -> sol cocuk yok
                    //yer var --> yeni dugum burada yer almalidir

                    if(current == null){
                        //sola ekle
                        parent.leftChild = newNode;
                        return;
                    }
                }//end if
            //deger kokten buyukse veya esitse sag alt agacta
                else {
                    //current dugumun sag alt agacina gecilir
                    current = current.rightChild;
                    //sag alt agac bos mu --> sag cocuk yok
                    //yer var -> yeni dugum burada yer almalidir
                    if(current == null){
                        parent.rightChild = newNode;
                        return;
                    }
                }//end else sag
            }//end while

        }//end else root doluysa
    } //end insert

    //agac yapisinin bos olmadigini farz edelim
    public Node find(long data){
            //root ile baslanir
        Node current = root;
        //anahtar degerle eslesme yoksa
        while(current.data != data){
            //aranan anliktan kucukse sola git
            if(data < current.data){
                current = current.leftChild;
            }
            //aranan anliktan buyukse saga git
            else {
                current = current.rightChild;
            }
            //cocuk dugumlerde yoksa yani bossa
            if(current == null){
                return null; //sayi bulunamadi
            }
        }//end while
        //Sayi bulunduysa
        return current;
    }

    public void traverse (int traverseType){
            switch (traverseType){
                case 1: {
                    System.out.print("\n PreOrder:");
                    preOrder(root);
                }
                case 2: {
                    System.out.print("\n InOrder:");
                    inOrder(root);
                }
                case 3: {
                    System.out.print("\n PostOrder:");
                    postOrder(root);
                }
            }
        System.out.println("");
    }//end traverse

    public void preOrder (Node localRoot){
            if(localRoot == null){
                //once kok
                System.out.print(localRoot.data + " ");
                //sonra sol alt
                preOrder(localRoot.leftChild);
                //en son sag alt
                preOrder(localRoot.rightChild);
            }
    }//end preOrder

    public void inOrder (Node localRoot){
            if(localRoot != null){
                //once sol alt
                inOrder(localRoot.leftChild);
                //sonra kok
                System.out.print(localRoot.data + " ");
                //en son sag alt
                inOrder(localRoot.rightChild);
            }
    }//end inOrder

    public void postOrder (Node localRoot){
            if(localRoot != null){
                //once sol alt
                postOrder(localRoot.leftChild);
                //sonra sag alt
                postOrder(localRoot.rightChild);
                //en son kok
                System.out.print(localRoot.data + " ");
            }
    }//end postOrder
    public Node minimum(){
            //roottan baslanir
        Node current = root;
        Node last = null;
        //sona kadar gidilir
        //sol dal boyunca ilerlenir
        while(current != null){
            //son ziyaret edilen dugumu sakla
            last = current;
            //sol cocugu gec
            current = current.leftChild;
        }
        //en sol dugumdeyizz
        //bu dugum, agacin en kucuk elemani
        return last;
    }//end minimum

    public boolean delete(long key){
            Node current = root;
            Node parent = root;
            //varsaydik
        boolean isLeftChild = true;

        //Silinecek dugumun konumu bulunuyor
        while (current.data != key){
            //silinecek dugumun parent'i
            parent=current;
            //Aranan deger current.data'dan kucuk ise sola git
            if(key<current.data){
                //silinecek sol cocuk
                isLeftChild = true ;
                current = current.leftChild;
            }
            //aranan deger current.data'dan buyuk ya da esit ise saga git
            else {
                //silinecek sag cocuk
                isLeftChild = false ;
                current = current.rightChild;
            }
            //agacin sonuna kadar gidilmis ama deger bulunamamis
            if(current == null){
                return false;
            }
        }//end while
        //silinecek dugum bulundu-------

        //DURUM 1:Child yoksa sadece sil
        if(current.leftChild == null && current.rightChild == null){
            //silinecek dugumun sag ve sol child dugumleri bossa
            //dugum kokse
            if(current == root){
                root = null;
            }
            //silinecek sol cocuksa
            else if(isLeftChild){
                //parent'in sol cocugu silindigi icin null
                //silinecek current=parent.leftChild

                parent.leftChild = null;
            }
            else {
                //parent'in sag cocugu silindigi icin null
                //silinecek current = parent.rightChild
                parent.rightChild = null;
            }
        }

        //DURUM 2: Sag child yok sol child varsa
        //sag child yoksa sol alt agacla yer degistir
        //silinecek dugumun sag child dugumu bossa
        else if(current.rightChild == null){
            //dugum kokse
            if(current == root){
                //root = sol cocuk
                root = current.leftChild;
            }
            //silinecek parent'in sol cocuguysa
            else if(isLeftChild){
                //parent sol cocuguna silinecek dugumun sol cocugunu ata
                parent.leftChild = current.leftChild;
            }
            //silinecek parent'in sag cocuguysa
            else {
                //parent sag cocuguna silinecek dugumun sol cocugunu ata
                parent.rightChild = current.leftChild;
            }
        }


        //DURUM 2: Sol child yok sag child varsa
        //sol child yoksa sag alt agacla yer degistir
        else if(current.leftChild == null){
            //dugum kokse
            if(current == root){
             //root =sag cocuk
             root = current.rightChild;
            }
            //silinecek parent'in sol cocuguysa
            else if (isLeftChild) {
                //parent sol cocuguna silinecek dugumun sag cocugunu ata
                parent.leftChild = current.rightChild;
            }
            else {
                //parent sag cocuguna silinecek dugumun sag cocugunu ata
                parent.rightChild = current.rightChild;
            }
            }


        //DURUM 3: Silinecek dugumun hem sol hem sag cocugu da varsa
        else{
            //current silinecek dugum, silinecek dugum yerine gececek successor bul
            Node successor = getSuccessor(current);
            if(current == root){
                root = successor;
            }
            //silinecek parent'in sol cocuguysa
            else if(isLeftChild){
                //parent'in leftChild'ina successor ata
                parent.leftChild = successor;
            }
            //silinecek sag cocuguysa parent'in rightChild'ina successor ata
            else {
                parent.rightChild = successor;
            }
            //successor'in sol cocugu silinen dugumun sol cocugu yap
            successor.leftChild = current.leftChild;
        }
        return true;
        } //end delete


        private Node getSuccessor(Node delNode){
            Node successorParent = delNode;
            Node successor = delNode;
            //once silinecek dugumun sag cocuguna git
            Node current =delNode.rightChild;

            while (current != null){
                successorParent = successor;
                successor = current;
                //sonra sureki sol cocuga git
                current = current.leftChild;
            } //sol cocuk null olunca dur successor bulundu

            //successor sag alt cocuk degil, sag cocugun sol torunuysa
            if(successor != delNode.rightChild){
                //successor parent sol cocugunu, successor sag cocugu yap
                successorParent.leftChild = successor.rightChild;
                //successor sag cocugunu silinecek dugumun sag cocugu yap
                successor.rightChild = successor.rightChild;
            }
            return successor;
        }


        public void displayTree(){
            //Global Stack nesne tipi node
            Stack<Node> globalStack = new Stack<Node>();
            //global stack baslangici olarak root yerlestir
            globalStack.push(root);
            int nBlanks =32;
            boolean isRowEmpty=false;
            System.out.println("......................................................");

            //satir bostan farkliysa calis
            //uani false oldugunda calis
            while(!isRowEmpty){
                //Local Stack
                Stack<Node> localStack =new Stack<Node>();
                //varsayilan deger olarak satir bos diye ata
                isRowEmpty =true;
                for(int j=0;j<nBlanks;j++){
                    //32 karakter bosluk koy
                    System.out.print(' ');
                }

            //global stack bos degilse
                while(!globalStack.isEmpty()){
                    //globalstack'ten Node tipine getir
                    //local stack'ten yerlesenlerden en soldan basla saga dogru getir
                    Node temp= (Node)globalStack.pop();
                    //temp doluysa stackten root -sol cocuk- sag cocuk seklinde veri cek
                    if(temp != null){
                        //root-sol cocuk sag cocuk seklinde verileri yaz
                        System.out.print(temp.data);
                        //localstack'e once sol cocugu yerlestir
                        localStack.push(temp.leftChild);
                        //sonra sag cocugu yerlestir
                        localStack.push(temp.rightChild);

                        //sag ya da sol cocuk varsa isRowEmpty=false
                        if(temp.leftChild != null || temp.rightChild != null){
                            //satir dolu
                            isRowEmpty =false;
                        }
                    }
                    //temp null'sa
                    else{
                        //bossa 2 tire ata
                        System.out.print("--");
                        localStack.push(null);
                        localStack.push(null);
                    }
                    //32 bosluk vardi sayilar arasi simdi 2 kati oldu
                    for(int j=0; j<nBlanks *2 - 2; j++){
                        System.out.print(' ');
                    }
                }//end while -> globaLstack bos degil
                System.out.println();

                nBlanks/=2;
                while(!localStack.isEmpty()){
                    //global stack'e local stackten gelenleri yerlestir
                    //once sag dugum sonra sol dugum
                    globalStack.push(localStack.pop());
                    //local stack'tekileri sagdan sola global stack'e yerlestir
                }
            }//end while ->isRowEmpty false
            System.out.println(".....................................................");
        }
}
