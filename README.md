# KNU2_FriendListBot

## Developer
- CuteIU
- ydhwa
- takgung6820
- byeoljubu

## Function
1. 친구 추가
> `@bot add <name> <age> <gender>`
2. 친구 삭제
> `@bot remove <name>`
3. 친구 찾기
> `@bot find <name>`
4. 친구 리스트
> `@bot list`
5. 현재 시간
> `@bot time`

## Constraints
- 친구는 최대 10명까지 추가
- 여러 예외상황에 대해서 적절한 메시지 출력
- Key = 친구의 name
- 친구를 추가할 때 반드시 모든 정보(name, age, gender)를 등록해야 함
- 별도의 Database를 사용하지 않기 때문에 bot을 껐다 키면 친구가 0명임

## 1000명 이상의 사람을 저장할 때 어떤 자료구조가 검색이 유용할 것인가?
순차 검색의 최악의 시간 복잡도는 `T(n) = n`이고, 이진 검색의 최악의 시간 복잡도는 `T(n) = log2(n)`이다. 따라서 많은 데이터가 저장되어 있을 때, 이진 검색 알고리즘을 사용하는 것이 많은 경우에 대해서 속도가 더 빠를 것이다.

이진 검색을 사용하려면 검색하려는 자료구조가 정렬되어 있어야 한다는 조건이 있고, 친구를 무제한으로 받을 수 있다면 수를 정해놓아야 하는 배열보다는 연결 리스트 형태가 적절하다.

ArrayList와 LinkedList를 비교해보자면, 배열을 기반으로 구현되어 포화 상태에 이를 때마다 새로 재조정해야 하고, 자료의 최대 개수에 영향을 받는 ArrayList보다는 몇 개의 참조자만 바꿈으로써 새로운 자료의 삽입이나 기존 자료의 삭제를 빠른 시간 안에 수행할 수 있는 LinkedList 자료구조를 사용하는 것이 좋다.

따라서, 1000명 이상의 사람을 저장할 때는 정렬된 연결 리스트(Sorted Linked List)를 사용하는 것이 효과적일 것으로 보인다.

## Sources
- 검색 알고리즘의 시간 복잡도: <http://zelord.tistory.com/11>
- Linked List와 Array List: <http://www.nextree.co.kr/p6506/>


