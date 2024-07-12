package api;

import game.Rule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RuleSet implements Iterable<Rule> {

    List<Rule> ruleList = new ArrayList<>();

    public void add(Rule boardRule) {
        ruleList.add(boardRule);
    }

    @Override
    public Iterator<Rule> iterator() {
        return ruleList.listIterator();
    }

    @Override
    public void forEach(Consumer<? super Rule> action) {
        ruleList.forEach(action);
    }

    @Override
    public Spliterator<Rule> spliterator() {
        return ruleList.spliterator();
    }
}
