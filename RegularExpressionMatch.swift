//
//  RegularExpressionMatch.swift
//  TheS30-SwiftTests
//
//  Created by Malav Soni on 10/01/25.
//

import Testing

struct RegularExpressionMatch {
    
    struct TestCase {
        let source: String
        let pattern: String
        let expected: Bool
    }
    
    static let testCases: [TestCase] = [
        TestCase(source: "aab", pattern: "c*a*b", expected: true),
        TestCase(source: "abc", pattern: "abc", expected: true),
        TestCase(source: "aa", pattern: "a", expected: false),
        TestCase(source: "aa", pattern: "a*", expected: true),
        TestCase(source: "abcd", pattern: ".*", expected: true),
        TestCase(source: "ab", pattern: ".*c", expected: false),
    ]
    
    func isMatch_dfs(_ s: String, _ p: String) -> Bool {
        
        var memorise:[[Int]] = [[Int]].init(repeating: [Int].init(repeating: 0, count: p.count+1), count: s.count+1)
        
        func travers(sIdx: Int, pIdx: Int) -> Bool {
            // Base Case
            if (pIdx == p.count) { // reached pattern end
                return sIdx == s.count
            }
            
            if (memorise[sIdx][pIdx] == 1) {
                return true
            } else if (memorise[sIdx][pIdx] == 2) {
                return false
            }
            
            // Logic
            let firstMatch:Bool = sIdx < s.count && pIdx < p.count && (p[pIdx] == s[sIdx] || p[pIdx] == ".")
            
            var result = false
            
            if (pIdx + 1 < p.count && p[pIdx + 1] == "*") { // if next char is * in pattern then we have a choose case
                result = travers(sIdx: sIdx, pIdx: pIdx + 2) || (firstMatch && travers(sIdx: sIdx + 1, pIdx: pIdx)) // +2 because we will have to jump to char after *
            } else {
                if (firstMatch) {
                    result = travers(sIdx: sIdx+1, pIdx: pIdx + 1)
                }
            }
            
            memorise[sIdx][pIdx] = result ? 1 : 2
            
            return result
        }
        
        return travers(sIdx: 0, pIdx: 0)
    }
    
    func isMatch(_ s: String, _ p: String) -> Bool {
        // Create Table Skeleton with false as default Value
        var dp:[[Bool]] = [[Bool]].init(repeating: [Bool].init(repeating: false, count: p.count + 1), count: s.count + 1)
        dp[0][0] = true // blank string in source matches blank pattern
        
        for i in 1..<dp[0].count {
            if p[i - 1] == "*" {
                dp[0][i] = dp[0][i - 2]
            }
        }
        
        
        for i in 1..<dp.count {
            for j in 1..<dp[i].count{
                
                let pChar = p[j - 1] // Pattern Charactor
                let sChar = s[i - 1]
                
                if (sChar == pChar || pChar == ".") {
                    dp[i][j] = dp[i - 1][j - 1]
                } else if pChar == "*" {
                    // Choose case only if previous char matches current source index or it's a .
                    let pPrevChar = p[j - 2]
                    let isChooseCaseAvailable:Bool = (sChar == pPrevChar || pPrevChar == ".")
                    if isChooseCaseAvailable {
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j]
                    } else {
                        dp[i][j] = dp[i][j - 2]
                    }
                }
            }
        }
        
        for i in 0..<dp.count {
            print(dp[i])
        }
        
        return dp[s.count][p.count]
    }
    
    @Test("Tabulation Logic", .tags(.dp, .tabulation), arguments: testCases)
    func evaluteRegularExpression(argument: TestCase) async throws {
        #expect(isMatch(argument.source, argument.pattern) == argument.expected)
    }
     
    @Test("DFS Logic", .tags(.dp, .dfs), arguments: testCases)
    func evaluteRegularExpressionWithDFS(argument: TestCase) async throws {
        #expect(isMatch_dfs(argument.source, argument.pattern) == argument.expected)
    }
    
}
