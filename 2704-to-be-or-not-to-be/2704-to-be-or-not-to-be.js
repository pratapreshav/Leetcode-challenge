var expect = function(val) {

    function toBe(vals) {
        if (val === vals) {
            return true;
        } else {
            throw new Error("Not Equal");
        }
    }

    function notToBe(vals) {
        if (val !== vals) {
            return true;
        } else {
            throw new Error("Equal");
        }
    }

    return {
        toBe,notToBe
    };
};