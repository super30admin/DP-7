//
//  EditDistance.swift
//  TheS30-SwiftTests
//
//  Created by Malav Soni on 12/01/25.
//

import Testing

struct EditDistance {
    
    struct TestCase {
        let word1:String
        let word2:String
        let editOperation:Int
    }
    
    static let testcases:[TestCase] = [
        TestCase(word1: "horse", word2: "ros", editOperation: 3)
    ]
    
    func minDistance_dfs_void_based(_ word1: String, _ word2: String) -> Int {
        
        var memo:[[Int]] = [[Int]](repeating: [Int].init(repeating: Int.max, count: word2.count + 1), count: word1.count + 1)
        
        var minOperation = Int.max
        
        func dfs(i:Int, j:Int, operation:Int) {
            if (j == word2.count) {
                minOperation = min(minOperation, (word1.count - i) + operation)
                return
            }
            else if (i == word1.count) {
                minOperation = min(minOperation, (word2.count - j) + operation)
                return
            }
            
            // no operation
            if (word1[i] == word2[j]) {
                dfs(i: i+1, j: j+1, operation: operation)
            } else {
                // delete
                dfs(i: i, j: j+1, operation: operation + 1)
                // add
                dfs(i: i+1, j: j, operation: operation + 1)
                // edit
                dfs(i: i+1, j: j+1, operation: operation + 1)
            }
        }
        
        dfs(i: 0, j: 0, operation: 0);
        
        return minOperation
    }
    
    func minDistance_dfs_int_based(_ word1: String, _ word2: String) -> Int {
        var memo:[[Int]] = [[Int]](repeating: [Int].init(repeating: Int.max, count: word2.count + 1), count: word1.count + 1)
        
        func dfs(i:Int, j:Int) -> Int {
            if (j == word2.count) {
                return word1.count - i
            }
            else if (i == word1.count) {
                return word2.count - j
            }
            
            if (memo[i][j] != Int.max) {
                return memo[i][j]
            }
            
            var result:Int = 0
            // no operation
            if (word1[i] == word2[j]) {
                result = dfs(i: i+1, j: j+1)
            } else {
                // delete
                let delete = dfs(i: i, j: j+1)
                // add
                let add = dfs(i: i+1, j: j)
                // edit
                let edit = dfs(i: i+1, j: j+1)
                result = 1 + min(add, edit, delete)
            }
            
            memo[i][j] = result
            
            return result
        }
        
        return dfs(i: 0, j: 0);
    }
    
    @Test("Edit Distance - DFS - Int based", .tags(.dp, .dfs), arguments: EditDistance.testcases)
    func editDistance_dfs_int_based(argument:TestCase) async throws {
        // Write your test here and use APIs like `#expect(...)` to check expected conditions.
        #expect(minDistance_dfs_int_based(argument.word1, argument.word2) == argument.editOperation)
    }
    
    @Test("Edit Distance - DFS - Void based", .tags(.dp, .dfs, .voidBasedRecursion), arguments: EditDistance.testcases)
    func editDistance_dfs_void_based(argument:TestCase) async throws {
        // Write your test here and use APIs like `#expect(...)` to check expected conditions.
        #expect(minDistance_dfs_void_based(argument.word1, argument.word2) == argument.editOperation)
    }
    
}
