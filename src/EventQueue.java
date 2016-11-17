import java.util.PriorityQueue;

public class EventQueue extends CPU {
    public PriorityQueue<ECB> osQ = new PriorityQueue<>();

    //singleton since we only ever need one
    private static EventQueue instance = null;
    protected EventQueue() {
    }
    public static EventQueue get() {
        if(instance == null) {
            instance = new EventQueue();
        }
        return instance;
    }

    public ECB getEvent(int lookingFor){
        for(ECB event: osQ){
            if(event.getEvent() == lookingFor){
                return event;
            }
        }
        return null; // look at this
    }

    public void enQueue(ECB incoming){
        osQ.add(incoming);

    }

    public void deQueue(ECB outgoing){
        osQ.remove(outgoing);

    }

    public int getSize(){
        return osQ.size();
    }
//    private static final int DEFAULT_CAPACITY = 1000;
//    private int currentSize;      // Number of elements in heap
//    private Comparable [ ] array; // The heap array
    // priority queue binary heap
//    public EventQueue(){
//        currentSize = 0;
//        array = new Comparable[ DEFAULT_CAPACITY + 1 ];
//    }

//    public EventQueue( Comparable [ ] items ) {
//        currentSize = items.length;
//        array = new Comparable[ items.length + 1 ];
//
//        for( int i = 0; i < items.length; i++ )
//            array[ i + 1 ] = items[ i ];
//        buildHeap( );
//    }
//
//    public PriorityQueue insert(Comparable x ) {
//        if( currentSize + 1 == array.length )
//            doubleArray( );
//
//        // Percolate up
//        int hole = ++currentSize;
//        array[ 0 ] = x;
//
//        for( ; x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
//            array[ hole ] = array[ hole / 2 ];
//        array[ hole ] = x;
//
//        return null;
//    }
//
//    public Comparable findMin( ) {
//        if( osQ.isEmpty( ) )
//            return currentSize == 0;
//        return array[ 1 ];
//    }
//
//    public Comparable deleteMin( ) {
//        Comparable minItem = findMin( );
//        array[ 1 ] = array[ currentSize-- ];
//        rollDown( 1 );
//
//        return minItem;
//    }
//
//    private void buildHeap( ) {
//        for( int i = currentSize / 2; i > 0; i-- )
//            rollDown( i );
//    }
//
//    private void rollDown( int hole ) {
//        int child;
//        Comparable tmp = array[ hole ];
//
//        for( ; hole * 2 <= currentSize; hole = child ) {
//            child = hole * 2;
//            if( child != currentSize &&
//                    array[ child + 1 ].compareTo( array[ child ] ) < 0 )
//                child++;
//            if( array[ child ].compareTo( tmp ) < 0 )
//                array[ hole ] = array[ child ];
//            else
//                break;
//        }
//        array[ hole ] = tmp;
//    }
//
//    private void doubleArray( ) {
//        Comparable [ ] newArray;
//
//        newArray = new Comparable[ array.length * 2 ];
//        for( int i = 0; i < array.length; i++ )
//            newArray[ i ] = array[ i ];
//        array = newArray;
//    }

    
}
