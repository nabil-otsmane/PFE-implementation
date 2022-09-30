const fs = require('fs')
const { Readable } = require('stream')

function generateTime(min, max, inclusiveMin = 1, inclusiveMax = 1) {
    return Math.floor(Math.random() * (max - min + inclusiveMax)) + min + 1 - inclusiveMin;
}

function generateInterval(wantedBegin, wantedEnd, max) {
    return [generateTime(0, wantedBegin), generateTime(wantedEnd, max)];
}

function generateIntervalPart(_wantedBegin, wantedEnd, max) {
    const begin = generateTime(0, wantedEnd, 1, 0)

    return [begin, generateTime(begin, max, 0, 1)];
}

function generateResourcesIterative(begin, end, n, generate) {
    const result = [];
    
    for (let i = 0; i < n; i++) {
        const [beginTime, endTime] = generate(begin, end, begin + end);
        const last = {
            id: i,
            name: "resource-"+(i+1),
            beginTime,
            endTime,
            shareable: false
        };

        result.push(last);
    }

    return result;
}

function generateFile(path, begin, end, n, fn) {
    const file = fs.createWriteStream(path, { encoding: "utf-8" })

    const input = Readable.from([JSON.stringify(generateResourcesIterative(begin, end, n, fn))]);

    input.pipe(file);
}

generateFile('test.json', 50, 80, 30, generateInterval);

// function main(prefix, fn) {
//     generateFile(prefix + "generated1.json", 69, 73, 15, fn);
//     generateFile(prefix + "generated2.json", 69, 73, 150, fn);
//     generateFile(prefix + "generated3.json", 69, 73, 1500, fn);
//     generateFile(prefix + "generated4.json", 69, 73, 15000, fn);
//     generateFile(prefix + "generated5.json", 69, 73, 150000, fn);
// }

// main("all_", generateInterval);
// main("part_", generateIntervalPart);

// function generationTest() {
//     const [begin, end] = [2, 5];
    
//     for (let i = 0; i < 5000000; i++) {
//         const [newBegin, newEnd] = generateInterval(begin, end, 10);

//         if (newBegin < 0 || newBegin > begin)
//             throw "wrong begin " + newBegin;
//         if (newEnd < end || newEnd > 10)
//             throw "wrong end " + newEnd;
//     }
// }