var createCounter = function(init) {
    let temp=init;
    return {
        
        increment: () => ++init,
        decrement: () => --init,
        reset: () =>{ init=temp; return init;}
    };
};
/**
 * const counter = createCounter(5)
 * counter.increment(); // 6
 * counter.reset(); // 5
 * counter.decrement(); // 4
 */