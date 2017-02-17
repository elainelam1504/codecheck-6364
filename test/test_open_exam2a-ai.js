var co = require('co');
var expect = require('expect');
var runAI = require('./lib.js').runAI;

describe('Exam 2A - AI', () => {
	it('should fail without arguments', () => co(function*() {
		try {
			yield runAI();
			return Promise.reject('exited with return code 0');
		} catch (e) {}
	}));

	it('should return the answer if word list consists of only one answer word', () => co(function*() {
		const start = 'tail';
		const words = ['less'];
		const expected = 'less';
		const actual = yield runAI(start, words);
		expect(actual).toEqual(expected);
	}));

	it('should return the answer if word list contains an answer in the middle', () => co(function*() {
		const start = 'tail';
		const words = ['more', 'less', 'cat'];
		const expected = 'less';
		const actual = yield runAI(start, words);
		expect(actual).toEqual(expected);
	}));
	it('should return the answer if word list contains an answer in the last', () => co(function*() {
		const start = 'tail';
		const words = ['more', 'cat', 'less', 'see', 'low'];
		const expected = 'low';
		const actual = yield runAI(start, words);
		expect(actual).toEqual(expected);
	}));
	
	it('should return the answer if word list contains an answer in the last', () => co(function*() {
		const start = 'tail';
		const words = ['more', 'cat', 'less', 'see', 'low', 'we'];
		const expected = 'less';
		const actual = yield runAI(start, words);
		expect(actual).toEqual(expected);
	}));
	
	it('should return the answer which bigest win probability', () => co(function*() {
		const start = 'tail';
		const words = ['more', 'cat', 'less', 'see', 'low', 'word', 'dump'];
		const expected = 'low';
		const actual = yield runAI(start, words);
		expect(actual).toEqual(expected);
	}));
	it('should return the answer which large word list', () => co(function*() {
		const start = 'watches';
		const words = ['President', 'Donald', 'goes', 'you', 'Trump', 'rejected', 'the', 'long-established', 'US', 'framework', 'for', 'Middle', 'East', 'peacemaking', 'at', 'a', 'White', 'House', 'visit', 'with', 'Israeli', 'Prime', 'Minister', 'Benjamin', 'Netanyahu', 'Wednesday', 'as', 'he', 'announced', 'his', 'desire', 'to', 'reach', 'the', 'ultimate', 'deal', 'In', 'staking', 'his', 'claim', 'to', 'a', 'prize', 'that', 'has', 'eluded', 'many', 'a', 'leader', 'before', 'him', 'Trump', 'previewed', 'the', 'nascent', 'outlines', 'of', 'an', 'approach', 'that', '--', 'if', 'he', 'sticks', 'with', 'it', '--', 'ditches', 'bipartisan', 'orthodoxy', 'borrows', 'some', 'old', 'ideas', 'and', 'Middle', 'East', 'experts', 'say', 'will', 'be', 'no', 'easier', 'to', 'pull', 'off', 'now', 'than', 'in', 'the', 'past', 'As', 'Trump', 'declared', 'his', 'deep', 'support', 'for', 'the', 'Jewish', 'state', 'he', 'abandoned', 'the', 'bedrock', 'principle', 'that', 'the'];
		const expected = 'say';
		const actual = yield runAI(start, words);
		expect(actual).toEqual(expected);
	}));
	
	it('should return the answer which large word list', () => co(function*() {
		const start = 'PP';
		const words = ['President', 'Donald', 'goes', 'you', 'Trump', 'rejected', 'the', 'long-established', 'US', 'framework', 'for', 'Middle', 'east', 'peacemaking', 'at', 'a', 'White', 'House', 'visit', 'with', 'Israeli', 'Prime', 'Minister', 'Benjamin', 'Netanyahu', 'Wednesday', 'this', 'see'];
		const expected = 'President';
		const actual = yield runAI(start, words);
		expect(actual).toEqual(expected);
	}));
	
});
