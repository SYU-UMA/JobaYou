import React from 'react';
import { Input } from 'reactstrap';
import styles from './ResumeCreate.module.scss';
import { PiLinkSimpleFill } from "react-icons/pi";
import { Button } from 'reactstrap';

const ResumeMyCareer = ({isViewInterviewList, isView, deleteMyCareer, modifyMyCareer, addMyCareer, myCareerData, setMyCareerData}) => {

	const [isAddOn ,setIsAddOn] = React.useState({
		myCareer: false,
		coreCompetence: false,
		url: false,
	});

	const [isEditOn, setIsEditOn] = React.useState(false);

	const handleChange = (e) => {
		const {id, value} = e.target;
		setMyCareerData((prevData) => ({
			...prevData,
			[id]: value,
		}))
	}
	
	const cancleButton = () => {
		setIsAddOn(prev => ({
			...prev,
			myCareer: false,
			coreCompetence: false,
			url: false,
		}))
		setIsEditOn(false);
	};

	const submitButton = () => {
		
		addMyCareer();
		
		cancleButton();
	}

	const editButton = () => {
		setIsEditOn(!isEditOn);
		setIsAddOn((prev) => ({
			myCareer: true,
			coreCompetence: true,
			url: true,
		}));
	}

	const editItem = () => {
		modifyMyCareer();
		
		cancleButton();
	}

	const deleteButton = () => {
		deleteMyCareer();

		cancleButton();
	}

    return (
        <div className='myCareer'>
					<h4>MY Career</h4>
					<hr />
					{ 	<div>
						{ (isViewInterviewList== false || isView == false) && myCareerData.job == null && isAddOn.myCareer == false && isEditOn == false && <div>
							<Button onClick={() => setIsAddOn({myCareer: true})}>추가</Button>
							<hr/>
							<h7>간단소개, 직무/직업, 핵심역량, URL을 입력하고 내 이력서를 브랜딩 해보세요!</h7> 
						</div>}
						{ isAddOn.myCareer == true && <div>
							<input 
								id='job'
								onChange={handleChange}
								className={styles.InputRequirements}
								type='text'
								value={myCareerData.job}
								placeholder='내 직무/직업'
							/>
							<h5>커리어 소개</h5><h7>내 커리어의 중요 내용만! 요약해서 기업에게 어필할 수 있어요</h7>
							<div>
								<Button>커리어 소개 자동생성</Button>
								<Input
								id='introduction'
								onChange={handleChange}
								className={styles.InputRequirements}
								type="textarea"
								value={myCareerData.introduction}
								placeholder="나를 어필할 수 있는 간단소개를 작성해보세요! 직접 작성이 어렵다면, 잡아유 커리어 브랜딩을 이용해보세요!"
								/>
								<Button onClick={() => setIsAddOn((prev) => ({...prev, 
									coreCompetence: !isAddOn.coreCompetence}))}>핵심역량</Button>
								<Button onClick={() => setIsAddOn((prev) => ({...prev, 
									url: !isAddOn.url}))}>URL</Button>
								{ isAddOn.coreCompetence == true && <div>
									<h5>핵심역량</h5><h7>어필하고 싶은 경험이나 핵심역량을 작성해보세요!</h7>
									{[...Array(5)].map((_, index) => (
											<Input
												key={index}
												onChange={handleChange}
												className={styles.InputRequirements}
												id={'coreCompetence' + (index+1)}
												type="text"
												value={myCareerData[`coreCompetence${index+1}`]}
												placeholder="[ex)데이터 분석, DB설계 등]"
											/>
									))}
								</div>}
								{ isAddOn.url == true && <div>
									<h5>URL 추가</h5><h7>Linkedin, Brunch, GitHub, Notion 등 개인 페이지 링크를 추가할 수 있어요.</h7>
									<Input
												className={styles.InputRequirements}
												onChange={handleChange}
												id='url'
												type="text"
												value={myCareerData.url}
												defaultValue="https://"
									/>
								</div>}
								{(isAddOn.myCareer && isEditOn == false) && <div>
									<Button onClick={() => cancleButton()} >취소</Button>
									<Button onClick={() => submitButton()}>저장</Button>	
								</div>}
								{ isEditOn && <div>
									<Button onClick={() => cancleButton()} >취소</Button>
									<Button onClick={() => editItem()}>편집</Button>	
								</div>}
							</div>
						</div>}
					</div>}
					{ myCareerData.job != null && <div> 
						{isEditOn == false && isAddOn.myCareer == false && 
						<div>
							{ (isViewInterviewList == false || isView == false) && <div>
								<Button onClick={() => deleteButton()}>삭제</Button>
								<Button onClick={() => editButton()}>편집</Button>
							</div>}
							<h4>{myCareerData.job}</h4>
							<p>{myCareerData.introduction}</p>
							{ (myCareerData.coreCompetence1 != null ||
							myCareerData.coreCompetence2 != null ||
							myCareerData.coreCompetence3 != null ||
							myCareerData.coreCompetence4 != null ||
							myCareerData.coreCompetence5 != null) &&
							<div>
								<p>[핵심역량]</p>
								{[...Array(5)].map((_, index) => (
									<p key={index}>{myCareerData[`coreCompetence${index+1}`]}</p>
								))}
							</div>}
							{ myCareerData.url != null && <div>	
								<h5>저에 대해 더 자세히 알고 싶으시다면?</h5>
								<a href={myCareerData.url} target='_blank'><PiLinkSimpleFill color='black' size='2em'/></a>
							</div>}
						</div>}
					</div>}
				</div>
    )
};

export default ResumeMyCareer;