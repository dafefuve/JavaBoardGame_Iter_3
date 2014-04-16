package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Daniel on 4/14/2014.
 */

public class HexBoard
{
    List<Edge> edges;

    public HexBoard()
    {
        edges = new List<Edge>()
        {
            @Override
            public int size()
            {
                return 0;
            }
            @Override
            public boolean isEmpty() {
                return false;
            }
            @Override
            public boolean contains(Object o) {
                return false;
            }
            @Override
            public Iterator<Edge> iterator() {
                return null;
            }
            @Override
            public Object[] toArray() {
                return new Object[0];
            }
            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }
            @Override
            public boolean add(Edge edge) {
                return false;
            }
            @Override
            public boolean remove(Object o) {
                return false;
            }
            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }
            @Override
            public boolean addAll(Collection<? extends Edge> c) {
                return false;
            }
            @Override
            public boolean addAll(int index, Collection<? extends Edge> c) {
                return false;
            }
            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }
            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }
            @Override
            public void clear() {

            }
            @Override
            public Edge get(int index) {
                return null;
            }
            @Override
            public Edge set(int index, Edge element) {
                return null;
            }
            @Override
            public void add(int index, Edge element) {

            }
            @Override
            public Edge remove(int index) {
                return null;
            }
            @Override
            public int indexOf(Object o) {
                return 0;
            }
            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }
            @Override
            public ListIterator<Edge> listIterator() {
                return null;
            }
            @Override
            public ListIterator<Edge> listIterator(int index) {
                return null;
            }
            @Override
            public List<Edge> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }

    public void defineEdge(Space s)
    {
        Space southS;               //The space directly below s (direction 2)
        if (s.getRow()+1 < 15)      //ensure that now space is created outside of the board
        {
            southS = new Space(s.getRow()+1,s.getColumn());
            this.edges.add(new Edge(s, southS, 0));
            defineEdge(southS);
        }

        Space southeastS;                   //The space southeast of s (direction 3)
        if (s.getColumn() % 2 == 0)         //even column
        {
            if (s.getColumn()+1 < 19)       //no space outside board
            {
                southeastS = new Space(s.getRow(), s.getColumn()+1);
                this.edges.add(new Edge(s, southeastS, 0));
                defineEdge(southeastS);
            }
        }
        else                                //odd column
        {
            if (s.getRow()+1 < 15 && s.getColumn()+1 < 19)      //no space outside board
            {
                southeastS = new Space(s.getRow()+1, s.getColumn()+1);
                this.edges.add(new Edge(s, southeastS, 0));
                defineEdge(southeastS);
            }
        }

        Space northeastS;               //The space northeast of s (direction 9)
        if (s.getColumn() % 2 == 0)     //even column
        {
            if (s.getRow()-1 >= 0 && s.getColumn()+1 < 19)       //no space outside board
            {
                northeastS = new Space(s.getRow()-1, s.getColumn()+1);
                this.edges.add(new Edge(s, northeastS, 0));
                defineEdge(northeastS);
            }
        }
        else                            //odd column
        {
            if (s.getColumn()+1 < 19)       //no space outside board
            {
                northeastS = new Space(s.getRow()+1, s.getColumn()+1);
                this.edges.add(new Edge(s, northeastS, 0));
                defineEdge(northeastS);
            }
        }
    }

    //returns a list of all the edges emanating from a given space
    public List<Edge> getEdges(Space oneGivenSpace)
    {
        List<Edge> edgesForSpace = new List<Edge>()
        {
            @Override
            public int size() {
                return 0;
            }
            @Override
            public boolean isEmpty() {
                return false;
            }
            @Override
            public boolean contains(Object o) {
                return false;
            }
            @Override
            public Iterator<Edge> iterator() {
                return null;
            }
            @Override
            public Object[] toArray() {
                return new Object[0];
            }
            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }
            @Override
            public boolean add(Edge edge) {
                return false;
            }
            @Override
            public boolean remove(Object o) {
                return false;
            }
            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }
            @Override
            public boolean addAll(Collection<? extends Edge> c) {
                return false;
            }
            @Override
            public boolean addAll(int index, Collection<? extends Edge> c) {
                return false;
            }
            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }
            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }
            @Override
            public void clear() {

            }
            @Override
            public Edge get(int index) {
                return null;
            }
            @Override
            public Edge set(int index, Edge element) {
                return null;
            }
            @Override
            public void add(int index, Edge element) {

            }
            @Override
            public Edge remove(int index) {
                return null;
            }
            @Override
            public int indexOf(Object o) {
                return 0;
            }
            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }
            @Override
            public ListIterator<Edge> listIterator() {
                return null;
            }
            @Override
            public ListIterator<Edge> listIterator(int index) {
                return null;
            }
            @Override
            public List<Edge> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        int i = 0;
        int numEdgesFound = 0;
        while (i < 285 && numEdgesFound < 6)
        {
            Space esOne = this.edges.get(i).getSpaceOne();
            Space esTwo = this.edges.get(i).getSpaceTwo();
            if (esOne.equals(oneGivenSpace) || esTwo.equals(oneGivenSpace))
            {
                edgesForSpace.add(this.edges.get(i));
                numEdgesFound++;
            }
        }

        return edgesForSpace;
    }

    //returns a list of all of the given space's neighbors
    public List<Space> getNeighbors(Space centerSpace)
    {
        List<Space> neighbors = new List<Space>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Space> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Space space) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Space> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Space> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Space get(int index) {
                return null;
            }

            @Override
            public Space set(int index, Space element) {
                return null;
            }

            @Override
            public void add(int index, Space element) {

            }

            @Override
            public Space remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Space> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Space> listIterator(int index) {
                return null;
            }

            @Override
            public List<Space> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        List<Edge> myEdges = this.getEdges(centerSpace);
        for (int i = 0; i < myEdges.size(); i++)
        {
            if (myEdges.get(i).getSpaceOne().equals(centerSpace))   //centerSpace is SpaceOne
            {
                neighbors.add(myEdges.get(i).getSpaceTwo());
            }
            else                                                    //centerSpace is SpaceTwo
            {
                neighbors.add(myEdges.get(i).getSpaceOne());
            }
        }

        return neighbors;
    }
}
