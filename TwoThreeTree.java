
// candidate number: 16776
		
class Node 			// Node for binary search tree
{
    int value;
    boolean isbrother = false; 	
    // default value for isbrother is initialized to false
    int innerdepth; // assign in the tree an innerdepth
    Node left;
    Node right;
    Node parent;
    Node (int x) 
    {
    	value = x;
    }
    
    /*Node (int x, Node parental) 
    {    
    	 value = x;
         parent = parental;
    }*/
     
    void print() 	// print the information about the treenode
    {
        System.out.println(); // extra line for clarity
        System.out.print(this);
        /*System.out.print(": key: " + value);*/
        System.out.print(" left: " + left);
        System.out.print(" right: " + right);
 		System.out.print(" parent: " + parent); 
        System.out.println(" isbrother: "+ isbrother);
    }
}

public class TwoThreeTree
{
    // A necessary tool, "lemma" so to speak
    
    // (1)
    boolean select(Node x) 		
    // select value x in the tree, return its node or null
    {
        boolean label = false;	// define a boolean label for the nodes, boolean
        
        Node p = root, parental = null;
        
        if (root.value != x.value) 
 		{
            label = true;
            while (p != null && p.value != x.value)
     		{
                parental = p;
                if (x.value > p.value)
                    {
                    	p = p.right;
                    }
                else 
               		{
                		p = p.left;
                	}
            }
            if (p != null)
            {
            	if (x.value < parental.value) 
      			{
      				parental.left = null;
      			}
                else 
                {
                	parental.right = null;
                }
            }	
        }
        return label;
    }  
    // end of (1)
    
    Node root = null;
    Node findinsert(Node x) 	
    // insert a new element if not present and return pointer to it
    { 
 		Node p = root, parental = null, grand_parent = null;
        while (p != null && p.value != x.value) 
 		{
            grand_parent = parental;
            parental = p;
            if (x.value < p.value)
               {
               		p = p.left;
               }
            else 
               {
            		p = p.right;
            	}
        }
 	if (p != null)  	// item was found, end
       {
            return x;
       }
 
        if (root == null) 		// have to change the root explicitly
 		{ 
            root = x;
            root.innerdepth = 0;
 		} 
 	else 		// Set up the conditions for the 5 different cases
 	{			// First check integer value, then boolean value
     if (parental.value > x.value && parental.isbrother == false 
         && ((parental.right.isbrother == false && parental.right != null )
         || parental.right == null)) 
         
         {
         	/*case1rotate(grand_parent, x, parental);*/
         }
     
     else if (parental.value < x.value && parental.isbrother == false 
              && (parental.left == null ||  parental.left != null)) 
   
         {
         	/*case2rotate(x, parental);*/
         }
   
     else if (parental.value > x.value && parental.isbrother == false 
              && parental.right.isbrother == true && parental.right != null) 
   
         {
         	/*case3rotate(x, parental);*/
         }
   
     else if (parental.value > x.value && parental.isbrother == true)
   
         {
           	 /*case4rotate(grand_parent, x, parental);*/
         }
            
     else if (parental.value < x.value && parental.isbrother == true) 
   
         {
           	 /*case5rotate(grand_parent, x, parental);*/
         }
 	}
 	 return x;
    }
    
  	// Instructions for case1rotate  
   /* void case1rotate(Node grand_parent, Node x, Node y)
    {
		y.isbrother = true;
        if (grand_parent != null)
 		{ 
        	if (x.value > grand_parent.value) 
        	{
        		grand_parent.right = x;
        	}
        	else 
        	{
        		grand_parent.left = x;
        	}
     		//x.parent = grand_parent;
     		//x.right.parent = y;
     		//y.parent = x;
        } 
 		else 
 		{
     		x.parent = null; root = x;
 		}
    }*/
    
  	// Instructions for case2rotate
    /*void case2rotate(Node y, Node x)
    {
 		y.isbrother = true;
    }
    
  	// Instructions for case3rotate
    void case3rotate(Node x, Node y)
    {
 		y.right.isbrother = false;
        if(select(y) == true)
            {
            	findinsert(y);
            }
    }
    
  	// Instructions for case4rotate
    void case4rotate(Node x, Node y, Node z)
    {
        z.isbrother = false;
 		if (y.left != null)
  		{
  			y.left.parent = x;
  		}
 		if (y.right != null)
  		{
  			y.right.parent = z;
  		}																		
        x.right = y.left;																		
        y.right = z;
 		z.parent = y;
        y.left = x;
 		x.parent = y;
        if (select(x) == true)
      	{
      		findinsert(y);
      	}
        else 
 		{
      		y.parent = null; root = y;
 		}
    }
    
  	// Instructions for case5rotate
    void case5rotate(Node x, Node z, Node y)
    {
        y.isbrother = false;
 		if (y.left != null)
  		{
  			y.left.parent = x;
  		}
        x.parent = y;
        y.left = x;
        if (select(x)) 
     	{
     		findinsert(y);
     	}
     	else
 		{
     		y.parent = null; root = y;
 		}
    }*/
    
    // this assigns the appropriate innerdepth in twothreetree
    void assign23innerdepth(Node p, Node parental)
    {
        if (p == null) 
  			{
  				return;
  			}
        if(p.value == root.value);
        else if(p.isbrother == false)
        		{
        			p.innerdepth = 1 + parental.innerdepth;	
        			// go on to the next depth
        		}
        		
       else
       			{
       				p.innerdepth = parental.innerdepth;
       			}
        assign23innerdepth(p.left, p);
        assign23innerdepth(p.right, p);
    }
    
    void final23innerdepth() 									
    {
        assign23innerdepth(root, null); // assign the appropriate innerdepth
    }
    
    void print()// give the output we are interested in, that is the sorted keys														
    {
        recprint(root);
        System.out.println();
	    }
    
    void recprint(Node p) 
    // finally, print the sorted keys with corresponding innerdepth attached 		
    {					
        if (p == null)
            {
            	return;
            }
        recprint(p.left);
        System.out.print(p.value + "[");
        if (p.isbrother == true) 
  			{
  				System.out.print("*");
  			}
        System.out.print(p.innerdepth + "] ");
        recprint(p.right);
    }
    
    void structureprint() 	// computer language output for the tree
    {					
        internalprint(root, 0);
    }
    
    void internalprint(Node p, int depth)
    {
        if (p == null)
            {
            	return;
            }
        for (int i=0; i < depth; i++)	// while (i < depth)
            {
            	System.out.print("   ");
            }
 				p.print();
 				//i++;				    // use if "while" loop is used
        internalprint(p.left, depth+1);
        internalprint(p.right, depth+1);
    }
    
    // (2)
    public static void main(String[] args) 
    {
        
        Tree T = new Tree();
        for (int i=0; i < args.length; i++)
 		{
            Node x = new Node(Integer.parseInt(args[i])); 
            // creates a new node for each key
            T.findinsert(x); 
            // inserts the nodes in the tress according to caseXrotate
        }
	
		// Ignore this, just testing random trees

            Node a = new Node(46);
            Node b = new Node(761);
            Node c = new Node(7);
            Node d = new Node(8);
            Node e = new Node(99);
            Node f = new Node(10);
            Node g = new Node(11);
            Node h = new Node(13);
            Node i = new Node(32);
            Node j = new Node(89);
            Node k = new Node(8745);
            Node l = new Node(54);
            Node m = new Node(288);
            Node n = new Node(263);
            Node o = new Node(4789);
            Node u = new Node(1);
            Node v = new Node (271);
            Node w = new Node (314);
            Node t = new Node (315);
            Node x = new Node (1005);
            Node y = new Node (171);
            Node z = new Node (105);
            Node s = new Node (153);
            Node q = new Node (69);
            Node r = new Node (909);
            Node � = new Node (621);
            Node a1 = new Node (71);
            Node a2 = new Node (345);
            Node a3 = new Node (321);
            Node a4 = new Node (789);
            Node a5 = new Node (129);
            Node a6 = new Node (121);
            Node a7 = new Node (742);
            Node a8 = new Node (22);
            Node a9 = new Node (23);
            Node a10 = new Node (100);
            Node a11 = new Node (147);
            Node a12 = new Node (478);
            Node a13 = new Node (500);
            Node a14 = new Node (780);
            Node a15 = new Node (1492);
            Node a16 = new Node (981);
            Node a17 = new Node (236);
            Node a18 = new Node (102);
            Node a19 = new Node (27);
            Node a20 = new Node (54);
            Node a21 = new Node (61);
            Node a22 = new Node (94);
            Node a23 = new Node (102);
            Node a24 = new Node (841);
            Node a25 = new Node (564);
            Node a26 = new Node (220);
            Node a27 = new Node (96);
            
            
            T.findinsert(a);
       		T.findinsert(b);
       		T.findinsert(c);
       		T.findinsert(d);
       		T.findinsert(e);
       		T.findinsert(f);
       		T.findinsert(g);
       		T.findinsert(h);
       		T.findinsert(i);
       		T.findinsert(j);
       		T.findinsert(k);
       		T.findinsert(l);
       		T.findinsert(m);
       		T.findinsert(n);
       		T.findinsert(n);
       		T.findinsert(o);
       		T.findinsert(u);
       		T.findinsert(v);
       		T.findinsert(w);
       		T.findinsert(t);
       		T.findinsert(x);
       		T.findinsert(y);
       		T.findinsert(z);
       		T.findinsert(s);
       		T.findinsert(q);
       		T.findinsert(r);
       		T.findinsert(�);
       		T.findinsert(a1);
       		T.findinsert(a2);
       		T.findinsert(a3);
       		T.findinsert(a4);
       		T.findinsert(a5);
       		T.findinsert(a6);
       		T.findinsert(a7);
       		T.findinsert(a8);
       		T.findinsert(a9);
       		T.findinsert(a10);
       		T.findinsert(a11);
       		T.findinsert(a12);
       		T.findinsert(a13);
       		T.findinsert(a14);
       		T.findinsert(a15);
       		T.findinsert(a16);
       		T.findinsert(a17);
       		T.findinsert(a18);
       		T.findinsert(a19);
       		T.findinsert(a20);
       		T.findinsert(a21);
       		T.findinsert(a22);
       		T.findinsert(a23);
       		T.findinsert(a24);
       		T.findinsert(a25);
       		T.findinsert(a26);
       		T.findinsert(a27);

        T.final23innerdepth();
        T.print();
        T.structureprint();
    }    
    // end of (2)
}
