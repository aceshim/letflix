<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<html>

<!-- 포스트 모든 그룹 퍼블릭만 남김, 그룹유저 테이블 없앰, 그룹테이블에 퍼블릭 이외에 모두 삭제 -->
update tb_post set group_id = 'g_00001';
drop table tb_groupuser;
delete from tb_group where group_id != 'g_00001';

purge recyclebin;
commit;




UPDATE tb_user SET user_pic='minsoo_lgchem' WHERE user_id='minsoo@lgchem.com'; 
UPDATE tb_user SET user_pic='chulsoo_lguplus' WHERE user_id='chulsoo@lguplus.com'; 
UPDATE tb_user SET user_pic='0hee_lgchem' WHERE user_id='0hee@lgchem.com'; 
UPDATE tb_user SET user_pic='mina_lgdisplay' WHERE user_id='mina@lgdisplay.com'; 
UPDATE tb_user SET user_pic='heungmin_lgtwins' WHERE user_id='heungmin@lgtwins.com'; 
UPDATE tb_user SET user_pic='daeho_lgelectronics' WHERE user_id='daeho@lgelectronics.com'; 
UPDATE tb_user SET user_pic='shinwoo_lgcns' WHERE user_id='shinwoo@lgcns.com'; 
UPDATE tb_user SET user_pic='joohan_lgcns' WHERE user_id='joohan@lgcns.com'; 
UPDATE tb_user SET user_pic='minhwa_lgcns' WHERE user_id='minhwa@lgcns.com'; 
UPDATE tb_user SET user_pic='jaehoon_lgcns' WHERE user_id='jaehoon@lgcns.com'; 
UPDATE tb_user SET user_pic='dongha_lgcns' WHERE user_id='dongha@lgcns.com'; 
UPDATE tb_user SET user_pic='shinib_lgdisplay' WHERE user_id='shinib@lgdisplay.com'; 
UPDATE tb_user SET user_pic='jalnan_lgelectronics' WHERE user_id='jalnan@lgelectronics.com'; 
UPDATE tb_user SET user_pic='jeongmin_lgcns' WHERE user_id='jeongmin@lgcns.com'; 
UPDATE tb_user SET user_pic='jaekook_lgcns' WHERE user_id='jaekook@lgcns.com'; 
UPDATE tb_user SET user_pic='jaeyeon_lgcns' WHERE user_id='jaeyeon@lgcns.com'; 
UPDATE tb_user SET user_pic='hoseok_lgcns' WHERE user_id='hoseok@lgcns.com'; 
UPDATE tb_user SET user_pic='donghoon_lgcns' WHERE user_id='donghoon@lgcns.com'; 
UPDATE tb_user SET user_pic='jaekoo_lgcns' WHERE user_id='jaekoo@lgcns.com'; 
UPDATE tb_user SET user_pic='sunhee_lgcns' WHERE user_id='sunhee@lgcns.com'; 
UPDATE tb_user SET user_pic='sungmin_lgcns' WHERE user_id='sungmin@lgcns.com'; 
UPDATE tb_user SET user_pic='joonmin_lgcns' WHERE user_id='joonmin@lgcns.com'; 
UPDATE tb_user SET user_pic='hyuntak_lgcns' WHERE user_id='hyuntak@lgcns.com'; 
UPDATE tb_user SET user_pic='minkyung_lgcns' WHERE user_id='minkyung@lgcns.com'; 
UPDATE tb_user SET user_pic='jeonghyun_lgcns' WHERE user_id='jeonghyun@lgcns.com'; 
UPDATE tb_user SET user_pic='hyunseok_lgcns' WHERE user_id='hyunseok@lgcns.com'; 
UPDATE tb_user SET user_pic='minsun_lgcns' WHERE user_id='minsun@lgcns.com'; 
UPDATE tb_user SET user_pic='dowoo_lgcns' WHERE user_id='dowoo@lgcns.com'; 
UPDATE tb_user SET user_pic='minjoo_lgcns' WHERE user_id='minjoo@lgcns.com'; 
UPDATE tb_user SET user_pic='hyeji_lgcns' WHERE user_id='hyeji@lgcns.com'; 
commit;


<head>
<title>LETFLIX에 오신것을 환영합니다</title>
</head>
<body>
<p>Test Commit입니다.</p>
<p>안녕하세요 test</p>
<p>
TB_TAG
----------------------------------
TAG_ID			TAG_NAME
----------------------------------
TAG_001			여행
TAG_002			종교
TAG_003			패션
TAG_004			음식/요리
TAG_005			경제/경영
TAG_006			물리
TAG_007			건축/인테리어
TAG_008			회화
TAG_009			음악
TAG_010			영화
TAG_011			역사
TAG_012			해외
TAG_013			의료/건강
TAG_014			국내
TAG_015			공학
TAG_016			동물
TAG_017			기계
TAG_018			스마트폰
TAG_019			언어
TAG_020			한의학
TAG_021			교육
TAG_022			법률
TAG_023			자동차
TAG_024			정치/외교
TAG_025			통계

insert into tb_post values('p_00023','shinwoo@lgcns.com','g_00012','pp_00023','런닝하러 갑시다',sysdate,'01.JPG','런닝코스를 추천하는 포스트입니다.');
insert into tb_post values('p_00024','dongha@lgcns.com','g_00001','pp_00024','슬기로운 자취생활',sysdate,'01.JPG','슬기롭게 자취하는 방법을 소개하는 포스트입니다.');
insert into tb_post values('p_00025','jaehoon@lgcns.com','g_00003','pp_00025','미쿡여행',sysdate,'01.JPG','슬기롭게 미국여행을 하는 방법을 소개하는 포스트입니다.');
insert into tb_post values('p_00026','joohan@lgcns.com','g_00001','pp_00026','스포츠카 소개',sysdate,'01.JPG','당신도 탈 수 있다! 멋진 스포츠카를 소개하는 포스트입니다.');
insert into tb_post values('p_00027','minhwa@lgcns.com','g_00002','pp_00027','부산사투리 배우자',sysdate,'01.JPG','마 쓰까 묵자! 부산사투리를 소개하는 포스트이다 아이가');
insert into tb_post values('p_00028','jeongmin@lgcns.com','g_00010','pp_00028','방탈출하러 갑시다',sysdate,'01.JPG','한 번도 탈출해본 적은 없습니다.');
insert into tb_post values('p_00029','jaekook@lgcns.com','g_00001','pp_00029','문과생의 코딩공부',sysdate,'01.JPG','문과생이 뭐 어때서요.');
insert into tb_post values('p_00030','jaeyeon@lgcns.com','g_00009','pp_00030','인왕산 맛집추천',sysdate,'01.JPG','산책도 하고 맛집도 가는 포스트입니다.');
insert into tb_post values('p_00031','hoseok@lgcns.com','g_00011','pp_00031','헬스왕',sysdate,'01.JPG','이 되고 싶은 포스트입니다.');
insert into tb_post values('p_00032','donghoon@lgcns.com','g_00013','pp_00032','중고거래 사기당한 ssul',sysdate,'01.JPG','중고거래 이렇게 하지 마세요.');



insert into tb_tagup values ('pp_00023', 'TAG_013');
insert into tb_tagup values ('pp_00024', 'TAG_004');
insert into tb_tagup values ('pp_00024', 'TAG_007');
insert into tb_tagup values ('pp_00025', 'TAG_001');
insert into tb_tagup values ('pp_00025', 'TAG_012');
insert into tb_tagup values ('pp_00026', 'TAG_023');
insert into tb_tagup values ('pp_00027', 'TAG_019');
insert into tb_tagup values ('pp_00028', 'TAG_007');
insert into tb_tagup values ('pp_00029', 'TAG_015');
insert into tb_tagup values ('pp_00030', 'TAG_001');
insert into tb_tagup values ('pp_00030', 'TAG_004');
insert into tb_tagup values ('pp_00031', 'TAG_013');
insert into tb_tagup values ('pp_00032', 'TAG_005');



insert into tb_groupuser values ('shinwoo@lgcns.com', 'g_00012', 'N');
insert into tb_groupuser values ('dongha@lgcns.com', 'g_00001', 'N');
insert into tb_groupuser values ('jaehoon@lgcns.com', 'g_00003', 'N');
insert into tb_groupuser values ('joohan@lgcns.com', 'g_00001', 'N');
insert into tb_groupuser values ('minhwa@lgcns.com', 'g_00002', 'N');
insert into tb_groupuser values ('jeongmin@lgcns.com', 'g_00010', 'N');
insert into tb_groupuser values ('jaekook@lgcns.com', 'g_00001', 'N');
insert into tb_groupuser values ('jaeyeon@lgcns.com', 'g_00009', 'N');
insert into tb_groupuser values ('hoseok@lgcns.com', 'g_00011', 'N');
insert into tb_groupuser values ('donghoon@lgcns.com', 'g_00013', 'N');

insert into tb_groupuser values ('shinwoo@lgcns.com', 'g_00009', 'N');
insert into tb_groupuser values ('dongha@lgcns.com', 'g_00009', 'N');
insert into tb_groupuser values ('jaehoon@lgcns.com', 'g_00009', 'N');
insert into tb_groupuser values ('joohan@lgcns.com', 'g_00009', 'N');
insert into tb_groupuser values ('minhwa@lgcns.com', 'g_00009', 'N');

insert into tb_groupuser values ('jeongmin@lgcns.com', 'g_00010', 'N');
insert into tb_groupuser values ('jaekook@lgcns.com', 'g_00010', 'N');
insert into tb_groupuser values ('jaeyeon@lgcns.com', 'g_00010', 'N');
insert into tb_groupuser values ('hoseok@lgcns.com', 'g_00010', 'N');
insert into tb_groupuser values ('donghoon@lgcns.com', 'g_00010', 'N');

insert into tb_groupuser values ('jaekoo@lgcns.com', 'g_00011', 'N');
insert into tb_groupuser values ('sunhee@lgcns.com', 'g_00011', 'N');
insert into tb_groupuser values ('sungmin@lgcns.com', 'g_00011', 'N');
insert into tb_groupuser values ('joonmin@lgcns.com', 'g_00011', 'N');
insert into tb_groupuser values ('hyuntak@lgcns.com', 'g_00011', 'N');
insert into tb_groupuser values ('minkyung@lgcns.com', 'g_00011', 'N');

insert into tb_groupuser values ('jeonghyun@lgcns.com', 'g_00012', 'N');
insert into tb_groupuser values ('hyunseok@lgcns.com', 'g_00012', 'N');
insert into tb_groupuser values ('minsun@lgcns.com', 'g_00012', 'N');
insert into tb_groupuser values ('dowoo@lgcns.com', 'g_00012', 'N');
insert into tb_groupuser values ('hyeji@lgcns.com', 'g_00012', 'N');

insert into tb_groupuser values ('shinwoo@lgcns.com', 'g_00013', 'N');
insert into tb_groupuser values ('jeongmin@lgcns.com', 'g_00013', 'N');
insert into tb_groupuser values ('sunhee@lgcns.com', 'g_00013', 'N');
insert into tb_groupuser values ('hyuntak@lgcns.com', 'g_00013', 'N');
insert into tb_groupuser values ('jeonghyun@lgcns.com', 'g_00013', 'N');
insert into tb_groupuser values ('hyeji@lgcns.com', 'g_00013', 'N');






insert into tb_groupuser values ('jeonghyun@lgcns.com', 'g_00009', 'Y');
insert into tb_groupuser values ('minkyung@lgcns.com', 'g_00010', 'Y');
insert into tb_groupuser values ('hyunseok@lgcns.com', 'g_00011', 'Y');
insert into tb_groupuser values ('minjoo@lgcns.com', 'g_00012', 'Y');
insert into tb_groupuser values ('dowoo@lgcns.com', 'g_00013', 'Y');






post_id		user_id					group_id	ppt_id			post_title				post_createdate		post_thumbnaile		post_description
-------------------------------------------------------------------------------------------------------------------------------------------------------------
P0001		shinwoo@lgcns.com		g_00001						재밌는 독일어 표현 배우기		sysdate				썸네일경로				독일어를 재미있게 배우는 포스트입니다.
P0002		dongha@lgcns.com		g_00001						효율적인 랜선집사 되기		sysdate				썸네일경로				온라인으로 고양이를 보는 방법을 설명하는 포스트입니다.
P0003		jaehoon@lgcns.com		g_00001						스테이크를 잘 굽는 방법		sysdate				썸네일경로				스테이크를 잘 굽는 방법을 설명하는 포스트입니다.
P0004		joohan@lgcns.com		g_00001						Let's 와인				sysdate				썸네일경로				와인을 잘 고르는 방법을 설명하는 포스트입니다.
P0005		minhwa@lgcns.com		g_00001						단풍국 101				sysdate				썸네일경로				캐나다를 설명하는 포스트입니다.
P0006		jeongmin@lgcns.com		g_00002						마블 시네마틱 유니버스		sysdate				썸네일경로				마블 영화를 설명하는 포스트입니다.
P0007		jaekook@lgcns.com		g_00002						남미에서 살아남기			sysdate				썸네일경로				남미 여행기를 설명하는 포스트입니다.
P0008		jaeyeon@lgcns.com		g_00002						서울 산책길 				sysdate				썸네일경로				서울 산책길을 설명하는 포스트입니다.
P0009		hoseok@lgcns.com		g_00002						당신을 위한 영화추천			sysdate				썸네일경로				장르별로 영화를 추천하는 포스트입니다.
P0010		donghoon@lgcns.com		g_00002						나는 왜 매운 것을 못먹는가		sysdate				썸네일경로				매운 음식을 잘 못먹는 이류를 설명하는 포스트입니다.
P0011		jaekoo@lgcns.com		g_00002						똑똑하게 집구하기			sysdate				썸네일경로				전세 집 잘 구하는 방법을 설명하는 포스트입니다.
P0012		sunhee@lgcns.com		g_00003						퍼스털 컬러				sysdate				썸네일경로				개인에게 맞은 컬러를 설명하는 포스트입니다.
P0013		sungmin@lgcns.com		g_00003						자동차 구매 모든 것			sysdate				썸네일경로				자동차 구매 팁을 설명하는 포스트입니다.
P0014		joonmin@lgcns.com		g_00003						관현악단 동아리 소개			sysdate				썸네일경로				관현악단을 소개하는 포스트입니다.
P0015		hyuntak@lgcns.com		g_00003						고객사 관리 방법				sysdate				썸네일경로				고객사를 관리하는 방법을 소개하는 포스트입니다.
P0016		minkyung@lgcns.com		g_00003						감염						sysdate				썸네일경로				감염과 약 성능에 대해 설명하는 포스트입니다.
P0017		jeonghyun@lgcns.com		g_00004						직장인들을 위한 2주 여행 남미편	sysdate				썸네일경로				직장인들을 위한 여행 팁을 소개하는 포스트입니다.
P0018		hyunseok@lgcns.com		g_00004						스케일링 법칙				sysdate				썸네일경로				물리 법칙에 대한 설명을 하는 포스트입니다.
P0019		minsun@lgcns.com		g_00004						스리랑카 근현대사			sysdate				썸네일경로				스리랑카의 근현대사를 설명하는 포스트입니다.
P0020		dowoo@lgcns.com			g_00004						DC 코믹스					sysdate				썸네일경로				DC코믹스 영화에 대한 소개를 하는 포스트입니다.
P0021		minjoo@lgcns.com		g_00004						이민주의 실전요리			sysdate				썸네일경로				마파두부를 만드는 법을 소개하는 포스트입니다.
P0022		hyeji@lgcns.com			g_00004						코타키나발루				sysdate				썸네일경로				코타키나발루 여행을 소개하는 포스트입니다.








TB_PPT
-----------------------------------------------------------------------------------------------------------------------
PPT_ID (PK)		USER_ID	(FK)			PPT_DATE		PPT_LOCATION		PPT_LENGTH		PPT_LIKE		PPT_VIEWS
-----------------------------------------------------------------------------------------------------------------------
pp_00001		sinwoo@lgcns.com		sysdate			ppt경로				21				0				0
pp_00002		dongha@lgcns.com		sysdate			ppt경로				11				0				0
pp_00003		jaehoon@lgcns.com		sysdate			ppt경로				10				0				0
pp_00004		joohan@lgcns.com		sysdate			ppt경로				16				0				0
pp_00005		minhwa@lgcns.com		sysdate			ppt경로				12				0				0
pp_00006		jungmin@lgcns.com		sysdate			ppt경로				12				0				0
pp_00007		jaekook@lgcns.com		sysdate			ppt경로				6				0				0
pp_00008		jaeyoon@lgcns.com		sysdate			ppt경로				7				0				0
pp_00009		hoseok@lgcns.com		sysdate			ppt경로				20				0				0
pp_00010		donghun@lgcns.com		sysdate			ppt경로				22				0				0
pp_00011		jaekoo@lgcns.com		sysdate			ppt경로				16				0				0
pp_00012		sunhee@lgcns.com		sysdate			ppt경로				17				0				0
pp_00013		sungmin@lgcns.com		sysdate			ppt경로				7				0				0
pp_00014		junmin@lgcns.com		sysdate			ppt경로				16				0				0
pp_00015		hyuntak@lgcns.com		sysdate			ppt경로				14				0				0
pp_00016		minkyung@lgcns.com		sysdate			ppt경로				12				0				0
pp_00017		junghyun@lgcns.com		sysdate			ppt경로				12				0				0
pp_00018		hyunseok@lgcns.com		sysdate			ppt경로				22				0				0
pp_00019		minsun@lgcns.com		sysdate			ppt경로				9				0				0
pp_00020		dou@lgcns.com			sysdate			ppt경로				7				0				0
pp_00021		minjoo@lgcns.com		sysdate			ppt경로				13				0				0
pp_00022		hyeji@lgcns.com			sysdate			ppt경로				19				0				0




TB_TAGUP
--------------------------------------
PPT_ID(FK)		 TAG_ID(FK)  
--------------------------------------
pp_00001		TAG_019
pp_00001		TAG_012
pp_00002		TAG_016
pp_00003		TAG_004
pp_00004		TAG_004
pp_00005		TAG_012
pp_00006		TAG_010
pp_00007		TAG_012
pp_00007		TAG_001
pp_00008		TAG_001
pp_00009		TAG_010
pp_00010		TAG_004
pp_00011		TAG_007
pp_00012		TAG_003
pp_00013		TAG_023
pp_00014		TAG_009
pp_00015		TAG_005
pp_00016		TAG_013
pp_00017		TAG_001
pp_00017		TAG_012
pp_00018		TAG_006
pp_00019		TAG_012
pp_00019		TAG_011
pp_00020		TAG_010
pp_00021		TAG_004
pp_00022		TAG_001
pp_00022		TAG_012




TB_GROUPUSER
------------------------------------------------------------
user_id(FK)				group_id(FK)	 	groupuser_admin	
------------------------------------------------------------
shinwoo@lgcns.com		g_00001				Y
dongha@lgcns.com		g_00007				Y
jaehoon@lgcns.com		g_00006				Y
joohan@lgcns.com		g_00004				Y
minhwa@lgcns.com		g_00001				N
jeongmin@lgcns.com		g_00008				Y
jaekook@lgcns.com		g_00003				Y
jaeyeon@lgcns.com		g_00002				Y
hoseok@lgcns.com		g_00008				N	
donghoon@lgcns.com		g_00002				N
jaekoo@lgcns.com		g_00002				N
sunhee@lgcns.com		g_00002				N
sungmin@lgcns.com		g_00001				N
joonmin@lgcns.com		g_00005				Y
hyuntak@lgcns.com		g_00001				N
minkyung@lgcns.com		g_00001				N
jeonghyun@lgcns.com		g_00003				N
hyunseok@lgcns.com		g_00001				N
minsun@lgcns.com		g_00003				N
dowoo@lgcns.com			g_00008				N
minjoo@lgcns.com		g_00006				N
hyeji@lgcns.com			g_00008				N


TB_GROUPUSER
----------------------------------------------------------
user_id	 			post_id			readhistory_readdate	
----------------------------------------------------------
shinwoo@lgcns.com	p0005			sysdate





</body>
</html>