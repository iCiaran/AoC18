package uk.co.ciaranmoran.aoc.challenges;

import java.util.*;

public class Day07 extends Challenge {
    public Day07() {
        super("07");
    }

    @Override
    String partA() {
        Map<Character, ArrayList<Character>> req = new HashMap<>();
        Set<Character> done = new HashSet<>();
        ArrayList<Character> todo = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++){
            todo.add(c);
        }

        for(String s : input) {
            if (!req.containsKey(s.charAt(36))) {
                req.put(s.charAt(36), new ArrayList<>());
            }
            req.get(s.charAt(36)).add(s.charAt(5));
        }

        StringBuilder order = new StringBuilder();
        while (done.size() < todo.size()) {
            ArrayList<Character> canDo = new ArrayList<>();
            for(char c : todo){
                if (done.contains(c)) continue;
                if (!req.containsKey(c) || checkCanDo(req.get(c), done)) {
                    canDo.add(c);
                }
            }
            order.append(canDo.get(0));
            done.add(canDo.get(0));
        }
        return order.toString();
    }

    boolean checkCanDo(ArrayList<Character> list, Set<Character> done){
        for(char c : list) {
            if (!done.contains(c)) return false;
        }
        return true;
    }

    class Worker {
        int timeRemaining;
        boolean busy;
        char currentTask;

        Worker(){
            timeRemaining = 0;
            busy = false;
            currentTask = ' ';
        }

        void addTask(char task) {
            currentTask = task;
            timeRemaining = task - 'A' + 61;
            busy = true;
        }

        boolean update() {
            if (!busy) return false;
            timeRemaining--;
            if (timeRemaining == 0) {
                busy = false;
            }
            return (timeRemaining == 0);
        }
    }

    @Override
    String partB() {
        Worker[] pool = new Worker[5];
        for (int i = 0; i < 5; i++) {
            pool[i] = new Worker();
        }

        Map<Character, ArrayList<Character>> req = new HashMap<>();
        Set<Character> done = new HashSet<>();
        ArrayList<Character> todo = new ArrayList<>();
        Set<Character> progress = new HashSet<>();
        for (char c = 'A'; c <= 'Z'; c++){
            todo.add(c);
        }

        for(String s : input) {
            if (!req.containsKey(s.charAt(36))) {
                req.put(s.charAt(36), new ArrayList<>());
            }
            req.get(s.charAt(36)).add(s.charAt(5));
        }

        int time = 0;

        while (done.size() < todo.size()) {
            for(int i = 0; i < 5; i++) {
                if (pool[i].busy && pool[i].update()) {
                    done.add(pool[i].currentTask);
                    progress.remove(pool[i].currentTask);
                }
            }

            ArrayList<Character> canDo = new ArrayList<>();
            for(char c : todo){
                if (done.contains(c)) continue;
                if (progress.contains(c)) continue;
                if (!req.containsKey(c) || checkCanDo(req.get(c), done)) {
                    canDo.add(c);
                }
            }

            for(int i = 0; i < 5; i++) {
                if (canDo.size() > 0 && !pool[i].busy) {
                    pool[i].addTask(canDo.get(0));
                    progress.add(canDo.get(0));
                    canDo.remove(0);
                }
            }

            time++;
        }

        return "" + (time-1);
    }
}
