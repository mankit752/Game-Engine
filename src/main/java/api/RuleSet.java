package api;

import game.Board;
import game.Rule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RuleSet<T extends Board> implements Iterable<Rule<T>> {

    List<Rule<T>> ruleList = new ArrayList<>();

    public void add(Rule<T> boardRule) {
        ruleList.add(boardRule);
    }

    @Override
    public Iterator<Rule<T>> iterator() {
        return ruleList.listIterator();
    }

    @Override
    public void forEach(Consumer<? super Rule<T>> action) {
        ruleList.forEach(action);
    }

    @Override
    public Spliterator<Rule<T>> spliterator() {
        return ruleList.spliterator();
    }
}
