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
~~순차 검색의 최악의 시간 복잡도는 `T(n) = n`이고, 이진 검색의 최악의 시간 복잡도는 `T(n) = log2(n)`이다. 따라서 많은 데이터가 저장되어 있을 때, 이진 검색 알고리즘을 사용하는 것이 많은 경우에 대해서 속도가 더 빠를 것이다.~~

~~이진 검색을 사용하려면 검색하려는 자료구조가 정렬되어 있어야 한다는 조건이 있고, 친구를 무제한으로 받을 수 있다면 수를 정해놓아야 하는 배열보다는 연결 리스트 형태가 적절하다.~~

~~ArrayList와 LinkedList를 비교해보자면, 배열을 기반으로 구현되어 포화 상태에 이를 때마다 새로 재조정해야 하고, 자료의 최대 개수에 영향을 받는 ArrayList보다는 몇 개의 참조자만 바꿈으로써 새로운 자료의 삽입이나 기존 자료의 삭제를 빠른 시간 안에 수행할 수 있는 LinkedList 자료구조를 사용하는 것이 좋다.~~

~~따라서, 1000명 이상의 사람을 저장할 때는 정렬된 연결 리스트(Sorted Linked List)를 사용하는 것이 효과적일 것으로 보인다.~~

데이터에 중복을 허용하지 않는다는 전제 하에, List 인터페이스보다는 Set 인터페이스를 사용하는 것이 적절해 보인다. Set 인터페이스에는 HashSet과 TreeSet이 있는데, TreeSet의 경우 트리에 데이터를 담으면서 값에 따라 정렬을 하여 순서가 정해진다. 따라서 TreeSet의 삽입 연산은 HashSet보다 성능이 좋지 않다. 하지만, 친구 목록을 사용자에게 보여줄 때 정렬된 상태로 보여주면 좋기 때문에, 우리는 TreeSet을 사용하여 자료구조를 구현할 예정이었는데, 삽입, 검색, 삭제 등의 연산을 편리하게 사용하기 위해, "이름"을 Key로 가지고 "이름, 나이, 성별" 정보를 Value로 가지는 TreeMap을 이용하여 구현할 것이다.

## 주의사항
- 허용된 명령어(add, remove, find, list, time)가 아닐 경우 Bot이 "Not command"라고 말합니다.
- [Code Analyze]-[Inspect Code]를 통해 Refactoring을 한 상태이지만, SlackBotApplication과 SlackBotService는 프로젝트를 수정하기 전부터 존재하고 있던 class이기 때문에 이 부분에서 발생하는 issue는 일부러 해결하지 않았습니다.
- 그리고, 기존 public으로 선언된 class들을 일부러 private로 변경하지 않았습니다.

## Sources
- 검색 알고리즘의 시간 복잡도: <http://zelord.tistory.com/11>
- Linked List와 Array List: <http://www.nextree.co.kr/p6506/>
- Collection 객체 무엇을 써야 할까?(Set, List, Map): <http://programmingfbf7290.tistory.com/entry/Collection-%EA%B0%9D%EC%B2%B4-%EB%AC%B4%EC%97%87%EC%9D%84-%EC%8D%A8%EC%95%BC-%ED%95%A0%EA%B9%8CSet-List-Map>

