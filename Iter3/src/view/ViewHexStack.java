package view;

import java.util.Stack;

/**
 * Created by Arnav on 4/19/14.
 */
public class ViewHexStack
{
    Stack<ViewHex> s;

    public ViewHexStack()
    {
        s = new Stack<ViewHex>();
    }

    public void pushIntoStack(ViewHex vh)
    {
        s.push(vh);
    }

    public ViewHex peekIntoStack()
    {
        return s.peek();
    }

    public void popFromStack(){s.pop();}
}
