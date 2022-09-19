package model;


import java.util.ArrayList;

public class ProductDAO { // DB역할

	ArrayList<ProductVO> ramen; // 라멘리스트
	ArrayList<ProductVO> toping; // 토핑 리스트
	ArrayList<ProductVO> side; // 사이드 리스트
	// ==========================재고량 없는것들===================
	ArrayList<ProductVO> water; // 물양 리스트
	ArrayList<ProductVO> spicy; // 메운맛 리스트
	ArrayList<ProductVO> result; // 결과를 모두 저장
	ArrayList<ProductVO> price; // 가격을 모두 저장

	// pk 시스템에서 부여
	private int ramenpk;
	private int topingpk;
	private int sidepk;
	private int waterpk;
	private int spicypk;

	// 생성자
	public ProductDAO() {
		// 리스트 생성
		ramen = new ArrayList<ProductVO>();
		toping = new ArrayList<ProductVO>();
		side = new ArrayList<ProductVO>();
		water = new ArrayList<ProductVO>();
		spicy = new ArrayList<ProductVO>();
		price = new ArrayList<ProductVO>();
		result = new ArrayList<ProductVO>();

		// 초기 pk값
		ramenpk = 1;
		topingpk = 1;
		sidepk = 1;
		waterpk = 1;
		spicypk = 1;




		// 1
		ProductVO ramenvo1 = new ProductVO();
		ramenvo1.setCnt(50);
		ramenvo1.setRamen("진라면");
		ramenvo1.setNum(ramenpk++);
		// 각 라면 가격또한 추가
		ramenvo1.setPrice(1500);
		ramen.add(ramenvo1);


		// 2
		ProductVO ramenvo2 = new ProductVO();
		ramenvo2.setCnt(50);
		ramenvo2.setRamen("짜파게티");
		ramenvo2.setNum(ramenpk++);
		ramenvo2.setPrice(2000);
		ramen.add(ramenvo2);
		// 3
		ProductVO ramenvo3 = new ProductVO();
		ramenvo3.setCnt(50);
		ramenvo3.setRamen("너구리");
		ramenvo3.setNum(ramenpk++);
		ramenvo3.setPrice(1500);
		ramen.add(ramenvo3);
		// 4
		ProductVO ramenvo4 = new ProductVO();
		ramenvo4.setCnt(50);
		ramenvo4.setRamen("팔도 비빔면");
		ramenvo4.setNum(ramenpk++);
		ramenvo4.setPrice(2500);
		ramen.add(ramenvo4);

		// 토핑
		// 1
		ProductVO topingvo1 = new ProductVO();
		topingvo1.setCnt(50);
		topingvo1.setToping("계란");
		topingvo1.setNum(topingpk++);
		topingvo1.setPrice(500);
		toping.add(topingvo1);
		// 2
		ProductVO topingvo2 = new ProductVO();
		topingvo2.setCnt(50);
		topingvo2.setToping("치즈");
		topingvo2.setNum(topingpk++);
		topingvo2.setPrice(1000);
		toping.add(topingvo2);
		// 3
		ProductVO topingvo3 = new ProductVO();
		topingvo3.setCnt(50);
		topingvo3.setToping("만두");
		topingvo3.setNum(topingpk++);
		topingvo3.setPrice(1500);
		toping.add(topingvo3);
		// 4
		ProductVO topingvo4 = new ProductVO();
		topingvo4.setCnt(50);
		topingvo4.setToping("김가루");
		topingvo4.setNum(topingpk++);
		topingvo4.setPrice(300);
		toping.add(topingvo4);

		// 사이드
		// 1
		ProductVO sidevo1 = new ProductVO();
		sidevo1.setCnt(0);
		sidevo1.setSide("공기밥");
		sidevo1.setNum(sidepk++);
		sidevo1.setPrice(1000);
		side.add(sidevo1);
		// 2
		ProductVO sidevo2 = new ProductVO();
		sidevo2.setCnt(0);
		sidevo2.setSide("콜라");
		sidevo2.setNum(sidepk++);
		sidevo2.setPrice(1500);
		side.add(sidevo2);
		// 3
		ProductVO sidevo3 = new ProductVO();
		sidevo3.setCnt(0);
		sidevo3.setSide("핫바");
		sidevo3.setNum(sidepk++);
		sidevo3.setPrice(2000);
		side.add(sidevo3);

		// 매운맛

		// 1
		// vo.setCnt(50); <======= 재고량은 없어도됨, 가격도 마찬가지
		ProductVO spicyvo1 = new ProductVO();
		spicyvo1.setSpicy("순한맛");
		spicyvo1.setNum(spicypk++);
		spicy.add(spicyvo1);
		// 2
		ProductVO spicyvo2 = new ProductVO();
		spicyvo2.setSpicy("보통맛");
		spicyvo2.setNum(spicypk++);
		spicy.add(spicyvo2);
		// 3
		ProductVO spicyvo3 = new ProductVO();
		spicyvo3.setSpicy("매운맛");
		spicyvo3.setNum(spicypk++);
		spicy.add(spicyvo3);

		// 물양 401 부터 시작
		// 1
		ProductVO watervo1 = new ProductVO();
		watervo1.setWater("적게");
		watervo1.setNum(waterpk++);
		water.add(watervo1);
		// 2
		ProductVO watervo2 = new ProductVO();
		watervo2.setWater("보통");
		watervo2.setNum(waterpk++);
		water.add(watervo2);
		// 3
		ProductVO watervo3 = new ProductVO();
		watervo3.setWater("많이");
		watervo3.setNum(waterpk++);
		water.add(watervo3);

	}

	// CRUD 메서드

	// Create(xxxInsert) //주석은 라멘에만 쓰여져있음
	// 일단 전부 구현했는데 요구분석에서 뭐 빠져야 하는거 같은데 내일 수정 할게요 ㅜㅜ(피곤해요)
	// 라멘 인서트
	public boolean ramenInsert(ProductVO ramenvo) {
		try {
			// 넣을수록 PK값은 1씩 증가 !
			ramenvo.setNum(ramenpk); // 2. 얘는 시스템에서 부여하니 모델에서 구현
			/*
			 * vo.setName(); 1.이친구들은 컨트롤러에서 구현 vo.setCnt();
			 */
			ramen.add(ramenvo);

			// 이때 넣어줬을시엔 메소드 output이 boolean 이므로 return 값 true
			return true;
			// 어떤 오류가 생길지 모르기때문에 Exception 클래스 활용
		} catch (Exception e) { // 실패시

			e.printStackTrace(); // 어떤 예외인지 출력문구
			System.out.println("로그 : Insert()에서 확인되지 않은 예외 발생 !");
			// 예외가 생겼으므로 false 값으로 반환
			return false;
		}
	}

	// 토핑 인서트
	public boolean topingInsert(ProductVO topingvo) {
		try {
			topingvo.setNum(topingpk);
			toping.add(topingvo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("로그 : Insert()에서 확인되지 않은 예외 발생 !");
			return false;
		}
	}

	// 사이드 인서트
	public boolean sideInsert(ProductVO sidevo) {
		try {
			sidevo.setNum(sidepk);
			side.add(sidevo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("로그 : Insert()에서 확인되지 않은 예외 발생 !");
			return false;
		}
	}

	// 물 인서트
	public boolean waterInsert(ProductVO watervo) {
		try {
			watervo.setNum(waterpk);
			water.add(watervo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("로그 : Insert()에서 확인되지 않은 예외 발생 !");
			return false;
		}
	}

	// 매운맛 인서트
	public boolean spicyInsert(ProductVO spicyvo) {
		try {
			spicyvo.setNum(spicypk);
			spicy.add(spicyvo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("로그 : Insert()에서 확인되지 않은 예외 발생 !");
			return false;
		}
	}

	// CRU'D'
	// Deleate(xxxDelete) //주석은 라멘에만 쓰여져있음
	// 1라멘 딜리트

	public boolean ramenDelete(ProductVO ramenvo) {
		for (int i = 0; i < ramen.size(); i++) { // 라면 pk를 찾기위해 포문 사용
			if (ramen.get(i).getNum() == ramenvo.getNum()) {
	            // 라멘번호가 뷰애서 입력한 번호가 맞다면
	            ramen.remove(i);
//	            for (int j = i; j <= ramen.size()-1; j++) {
//	               ramen.get(j).setNum(j+1);   // i번째 인덱스를 삭제하였으니 i번째 들어온 인덱스를 1을 더해줘야 기존에 출력된 번호와 일치해진다.
//	            }
			}
		}
		// 제거하지 못햇다면
		// System.out.println("입력한 번호를 잘못입력햇스비낟");<=== 애는 뷰에서 구현
		return false;
	}

	// 2 토핑 딜리트
	public boolean topingDelete(ProductVO topingvo) {
		for (int i = 0; i < toping.size(); i++) {
			if (toping.get(i).getNum() == topingvo.getNum()) {
	            // 라멘번호가 뷰애서 입력한 번호가 맞다면
	            toping.remove(i);
	            for (int j = i; j <= toping.size()-1; j++) {
	               toping.get(j).setNum(j+1);   // i번째 인덱스를 삭제하였으니 i번째 들어온 인덱스를 1을 더해줘야 기존에 출력된 번호와 일치해진다.
	            }
			}
		}
		return false;
	}

	// 3 사이드 딜리트
	public boolean sideDelete(ProductVO sidevo) {
		for (int i = 0; i < side.size(); i++) {
			if (side.get(i).getNum() == sidevo.getNum()) {
	            // 라멘번호가 뷰애서 입력한 번호가 맞다면
	            side.remove(i);
	            for (int j = i; j <= side.size()-1; j++) {
	               side.get(j).setNum(j+1);   // i번째 인덱스를 삭제하였으니 i번째 들어온 인덱스를 1을 더해줘야 기존에 출력된 번호와 일치해진다.
	            }
			}
		}
		return true;
	}

	// 4 물딜리트
	public boolean waterDelete(ProductVO watervo) {
		for (int i = 0; i < water.size(); i++) {
			if (water.get(i).getNum() == watervo.getNum()) {
				water.remove(i);
				return true;
			}
		}
		return false;
	}

	// 5 매운맛 딜리트
	public boolean spicyDelete(ProductVO spicyvo) {
		for (int i = 0; i < spicy.size(); i++) {
			if (spicy.get(i).getNum() == spicyvo.getNum()) {
				spicy.remove(i);
				return true;
			}
		}
		return false;
	}
	// CR'U'D
	// 업데이트는 cnt (재고량) 변화를 줄때 사용
	// Update(xxxUpdate) //주석은 라멘에만 쓰여져있음
	// 1라멘 업뎃

	// 가격까지 고려했을때 재고수를 빼야하지 않나.. 요거 고려해보기

	public boolean ramenUpdate(ProductVO ramenvo) {
		for (ProductVO data : ramen) { // 포 이치 문으로 리스트를 data에 저장
			if (data.getNum() == ramenvo.getNum()) { // 저장된 번호와 입력한 번호가 같다면
				int num = data.getCnt() + ramenvo.getCnt();
				if(num > 0) {
					data.setCnt(data.getCnt() + ramenvo.getCnt());// 입력한 재고수 만큼 추가
					return true; // 참값을 반환후 메서드 종료
				}
			}
		}
		return false;
	}

	// 2. 사이드 업뎃
	public boolean sideUpdate(ProductVO sidevo) {
		for (ProductVO data : side) {
			if (data.getNum() == sidevo.getNum()) {
				int num = data.getCnt() + sidevo.getCnt();
				if(num > 0) {
					data.setCnt(data.getCnt() + sidevo.getCnt());// 입력한 재고수 만큼 추가
					return true; // 참값을 반환후 메서드 종료
				}
			}
		}
		return false;
	}

	// 3 토핑 없덱
	public boolean topingUpdate(ProductVO topingvo) {
		for (ProductVO data : toping) {
			if (data.getNum() == topingvo.getNum()) {
				int num = data.getCnt() + topingvo.getCnt();
				if(num > 0) {
					data.setCnt(data.getCnt() + topingvo.getCnt());// 입력한 재고수 만큼 추가
					return true; // 참값을 반환후 메서드 종료
				}
			}
		}
		return false;
	}

	// 물양이나 매운맛은 재고가 없으니 미구현

	// C'R'UD ==read
	// select는 selcetOne selcetOne 으로 나뉨
	// 요 위에 주석이 selectOne 이랑 selectAll 이라고 쓰신건지는 모르겠지만..
	// selectxxx(xxxselectxxxx) //주석은 라멘에만 쓰여져있음
	//=========================내일 하자 =====================================

	// selectOne 각 품목별로 구현
	public ProductVO ramenselectOne(ProductVO ramenvo) {
		// foreach 문을 활용해 ramen 배열리스트를 각각 data로 치환
		for (ProductVO data : ramen) {
			// 만약 data 키값과 vo 키값이 같다면
			if (data.getNum() == ramenvo.getNum()) {
				// data 값을 하나 반환한다.
				return data;
			}
		}
		return null;

	}

	public ProductVO topingselectOne(ProductVO topingvo) {
		for (ProductVO data : toping) {
			// 만약 data 키값과 vo 키값이 같다면
			if (data.getNum() == topingvo.getNum()) {
				// data 값을 하나 반환한다.
				return data;
			}
		}
		return null;
	}

	public ProductVO sideselectOne(ProductVO sidevo) {
		for (ProductVO data : side) {
			// 만약 data 키값과 vo 키값이 같다면
			if (data.getNum() == sidevo.getNum()) {
				// data 값을 하나 반환한다.
				return data;
			}
		}
		return null;
	}

	public ProductVO waterselectOne(ProductVO watervo) {
		for (ProductVO data : water) {
			// 만약 data 키값과 vo 키값이 같다면
			if (data.getNum() == watervo.getNum()) {
				// data 값을 하나 반환한다.
				return data;
			}
		}
		return null;
	}

	public ProductVO spicyselectOne(ProductVO spicyvo) {
		for (ProductVO data : spicy) {
			// 만약 data 키값과 vo 키값이 같다면
			if (data.getNum() == spicyvo.getNum()) {
				// data 값을 하나 반환한다.
				return data;
			}
		}
		return null;
	}

	public ArrayList<ProductVO> ramenselectAll(ProductVO ramenvo) {
		// vo에 이름이 있다면 즉 검색을 이용했다면
		if (ramenvo.getRamen() != null) {
			// 검색 [서비스]
			// 검색 결과를 돌려주겠다.
			ArrayList<ProductVO> ramen = new ArrayList<ProductVO>();
			for (int i = 0; i < this.ramen.size(); i++) {
				// 원래 있던 이름과 방금 검색할때 받은 이름을 비교한다.
				if (this.ramen.get(i).getRamen().equals(ramenvo.getRamen())) {
					// 같다라는 결론이 나오면 추가한다.
					ramen.add(this.ramen.get(i));
				}
			}
			return this.ramen;
		}
		return this.ramen;
	}


	public ArrayList<ProductVO> topingselectAll(ProductVO topingvo) {
		// vo에 이름이 있다면 즉 검색을 이용했다면
		if (topingvo.getToping() != null) {
			// 검색 [서비스]
			// 검색 결과를 돌려주겠다.
			ArrayList<ProductVO> toping = new ArrayList<ProductVO>();
			for (int i = 0; i < this.toping.size(); i++) {
				// 원래 있던 이름과 방금 검색할때 받은 이름을 비교한다.
				if (this.toping.get(i).getToping().equals(topingvo.getToping())) {
					// 같다라는 결론이 나오면 추가한다.
					toping.add(this.toping.get(i));
				}
			}
			return this.toping;
		}
		return this.toping;
	}

	public ArrayList<ProductVO> sideselectAll(ProductVO sidevo) {
		// vo에 이름이 있다면 즉 검색을 이용했다면
		if (sidevo.getSide() != null) {
			// 검색 [서비스]
			// 검색 결과를 돌려주겠다.
			ArrayList<ProductVO> side = new ArrayList<ProductVO>();
			for (int i = 0; i < this.side.size(); i++) {
				// 원래 있던 이름과 방금 검색할때 받은 이름을 비교한다.
				if (this.side.get(i).getSide().equals(sidevo.getSide())) {
					// 같다라는 결론이 나오면 추가한다.
					side.add(this.side.get(i));
				}
			}
			return this.side;
		}
		return this.side;
	}

	public ArrayList<ProductVO> waterselectAll(ProductVO watervo) {
		// vo에 이름이 있다면 즉 검색을 이용했다면
		if (watervo.getWater() != null) {
			// 검색 [서비스]
			// 검색 결과를 돌려주겠다.
			ArrayList<ProductVO> water = new ArrayList<ProductVO>();
			for (int i = 0; i < this.water.size(); i++) {
				// 원래 있던 이름과 방금 검색할때 받은 이름을 비교한다.
				if (this.water.get(i).getWater().equals(watervo.getWater())) {
					// 같다라는 결론이 나오면 추가한다.
					water.add(this.water.get(i));
				}
			}
			return water;
		}
		return this.water;
	}

	public ArrayList<ProductVO> spicyselectAll(ProductVO spicyvo) {
		// vo에 이름이 있다면 즉 검색을 이용했다면
		if (spicyvo.getSpicy() != null) {
			// 검색 [서비스]
			// 검색 결과를 돌려주겠다.
			ArrayList<ProductVO> spicy = new ArrayList<ProductVO>();
			for (int i = 0; i < this.spicy.size(); i++) {
				// 원래 있던 이름과 방금 검색할때 받은 이름을 비교한다.
				if (this.spicy.get(i).getSpicy().equals(spicyvo.getSpicy())) {
					// 같다라는 결론이 나오면 추가한다.
					spicy.add(this.spicy.get(i));
				}
			}
			return spicy;
		}
		return this.spicy;
	}

}