import java.util.ArrayList;

public class AspectDeps {
ArrayList<OpinionWord> deps  ;
AspectDeps()
{
    deps=new ArrayList<OpinionWord>(100);
}
public void Adddep (OpinionWord e)
{
    deps.add(e);
}
public void removedep (OpinionWord e)
{
    deps.remove(e);
}
public String ToString()
{   String init="i contain ";
    for (OpinionWord o: deps)
    {
        init+=" "+o.toString();
    }
    return init ;
}
}
