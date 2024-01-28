import React, { useEffect } from 'react';

import { useNavigate, Navigate } from "react-router-dom";

import styles from './Resume.module.scss';

import { Button } from 'reactstrap';
import ResumeMyCareer from './ResumeMyCareer';
import ResumeSkill from './ResumeSkill';
import ResumeBasic from './ResumeBasic';
import ResumeCareer from './ResumeCareer';
import AxiosInstance from '../../../api/AxiosInstance';
import { useRecoilState } from 'recoil';
import { interviewDataState, isLoginState} from '../../../recoil/RecoilState'
import StepDot from '../../../components/StepDot/StepDot';

const Resume = (
    {
        getResumeList, 
        modalIsOpen, 
        setModalIsOpen, 
        resumeData,
        getCareerList,
        setResumeData,
        isView,
        isViewInterviewList,
    }) => {

	const navigate = useNavigate();
  
  	const movePage = (url) => {
    	navigate(url, {state: {resumeNum: resumeData.resumeNum}})
  	};

    const [isLogin, setIsLogin] = useRecoilState(isLoginState);

	const closeModal = () => {
		setModalIsOpen(!modalIsOpen);
    }
    //page 관리
    const [currentPage, setCurrentPage] = React.useState(1);
    const maxRenderCount = 5;
    const maxPage = 4;

    const prevDisabled = (currentPage == 1);
    const nextDisabled = (currentPage == maxPage);

	const [basicData, setBasicData] = React.useState({
			name: '',
			career: '',
			birth: '',
			email: '',
			phone: '',
			address: '',
			resumeName: '',
	});
    //서버에 저장된 경력
	const [careerDynamicArray, setCareerDynamicArray] = React.useState([]);

	const [myCareerData, setMyCareerData] = React.useState({
		job: '',
		introduction: '',
		coreCompetence1: '',
        coreCompetence2: '',
        coreCompetence3: '',
        coreCompetence4: '',
        coreCompetence5: '',
		url: '',
	});

	const [skillDynamicArray, setSkillDynamicArray] = React.useState([]);
    
    const handleChange = (e) => {
		const {id, value} = e.target;
		setBasicData((prevData) => ({
			...prevData,
			[id]: value,
		}))
	}

    //토큰으로 user정보 받아오기
    const userInfo = () => {
        AxiosInstance
        .get('/api/auth/info')
        .then((response) => {
            setBasicData((prev) => ({
            ...prev,
            name: response.data.userName,
            birth: response.data.userBirthday,
            phone: response.data.userPhone,
            email: response.data.userEmail,
          }))
        })
      }
    
    //resumeBasic을 불러오는 기능
    const getResumeBasic = () => {
        AxiosInstance
        .get('/resume/list/num', {
            params: {resumeNum: resumeData.resumeNum},
        })
        .then((response) => {
            setBasicData((prev) => ({
                ...prev,
                career: response.data.career,
                resumeName: response.data.resumeName,
                address: response.data.userAddress,
            }))
        })
    };
    //resumeBasic을 수정하는 기능
    const modifyResumeBasic = () => {
        AxiosInstance
        .post('/resume/modify', {
            "userId": '',
            "resumeNum": resumeData.resumeNum,
            "resumeName": basicData.resumeName,
            "career": basicData.career,
            "userAddress": basicData.address,
        })
        .then(getResumeList)
    };
    //이력서를 삭제하는 기능
    const deleteResume = () => {
        AxiosInstance
        .delete('/resume/delete', {
            params: {resumeNum: resumeData.resumeNum,}
        })
        .then((response) => {
            alert('삭제 완료!')
        })
        .catch((error) => {
            console.error(error); // 오류 처리
        })
        .then(getResumeList)
        .then(() => closeModal())
    };

    //career들을 resumeData와 careerDynamicArray에 넣는 기능
    const getCareer = (resumeNum) => {
        AxiosInstance
        .get('/career/list', {
        params : {
        resumeNum: resumeNum,}
        })
        .then((response) => {
            setResumeData((prev) => ({
                ...prev,
                careerNums: response.data,
            }))
        })
        .catch((error) => {
        console.error('Error fetching getCareer:', error);
        });
    };
    //career를 서버에 저장하는 기능
    const addCareer = (careerData) => {
        AxiosInstance
        .post('/career/add', {
            "userId": '',
            "resumeNum": resumeData.resumeNum,
            "companyName": careerData.companyName,
            "retire": careerData.retire,
            "careerStart": careerData.careerStart,
            "careerEnd": careerData.careerEnd,
            "jobGradeDuties": careerData.jobGradeDuties,
            "contents": careerData.contents,
            "deptName": careerData.deptName,
            "salary": careerData.salary,
            "currency": careerData.currency,
            "area": careerData.area,
        })
        .then(() => getCareer(resumeData.resumeNum))
        .catch((error) => {
            console.error('Error fetching getCareer:', error);
            });
    };
    //Career 수정기능
    const modifyCareer = (careerNum, careerData) => {
        AxiosInstance
        .post('/career/modify', {
            "userId": '',
            "careerNum": careerNum,
            "resumeNum": resumeData.resumeNum,
            "companyName": careerData.companyName,
            "retire": careerData.retire,
            "careerStart": careerData.careerStart,
            "careerEnd": careerData.careerEnd,
            "jobGradeDuties": careerData.jobGradeDuties,
            "contents": careerData.contents,
            "deptName": careerData.deptName,
            "salary": careerData.salary,
            "currency": careerData.currency,
            "area": careerData.area,
        })
        .then(() => getCareer(resumeData.resumeNum))
        .then(() => console.log("modifyCareer Done!"))
        .catch((error) => {
            console.error('Error fetching getCareer:', error);
            });
    }
    //career 삭제 기능
    const deleteCareer = (careerNum) => {
        AxiosInstance
        .delete('/career/delete', {
            params: { resumeNum: resumeData.resumeNum,
                careerNum: careerNum
            }
        })
        .then(() => getCareer(resumeData.resumeNum))
        .then(() => console.log("deleteCareer Done!"))
        .catch((error) => {
        console.error('Error fetching getCareer:', error);
        });
    }

    //skill 목록을 가져오는 기능
    const getSkill = () => {
        AxiosInstance
        .get('/skill/list', {
            params: {
                resumeNum: resumeData.resumeNum,
            }
        })
        .then((response) => {
            setSkillDynamicArray(response.data);
        })
        .catch((error) => {
        console.error('Error fetching getCareer:', error);
        });
    };
    //skill을 저장하는 기능
    const addSkill = (skill) => {
        AxiosInstance
        .post('/skill/add', {
            userId: '',
            resumeNum: resumeData.resumeNum,
            skill: skill
        })
        .then(() => getSkill())
        .catch((error) => {
        console.error('Error fetching getCareer:', error);
        });
    };
    //skill을 삭제하는 기능
    const deleteSkill = (skillNum) => {
        AxiosInstance
        .delete('skill/delete', {
            params: {resumeNum: resumeData.resumeNum,
                skillNum: skillNum,
            }
        })
        .then(() => getSkill())
        .catch((error) => {
        console.error('Error fetching getCareer:', error);
        });
    };
    //myCareer를 가져오는 기능
    const getMyCareer = () => {
        AxiosInstance
        .get('/mycareer/select', {
            params: {
                resumeNum: resumeData.resumeNum,
            }
        })
        .then((response) => {
            setResumeData((prev) => ({
                ...prev,
                myCareerNum: response.data.myCareerNum,
            }))
            setMyCareerData((prev) => ({
                ...prev,
                job: response.data.job,
		        introduction: response.data.introduction,
		        coreCompetence1: response.data.coreCompetence1,
                coreCompetence2: response.data.coreCompetence2,
                coreCompetence3: response.data.coreCompetence3,
                coreCompetence4: response.data.coreCompetence4,
                coreCompetence5: response.data.coreCompetence5,
		        url: response.data.url,
            }))})
            .catch((error) => {
                console.error('Error fetching getMyCareer:', error);
                });
    };
    //myCareer를 추가하는 기능
    const addMyCareer = () => {
        AxiosInstance
        .post('/mycareer/add', {
            userId: '',
            resumeNum: resumeData.resumeNum,
            job: myCareerData.job,
            introduction: myCareerData.introduction,
            coreCompetence1: myCareerData.coreCompetence1,
            coreCompetence2: myCareerData.coreCompetence2,
            coreCompetence3: myCareerData.coreCompetence3,
            coreCompetence4: myCareerData.coreCompetence4,
            coreCompetence5: myCareerData.coreCompetence5,
            url: myCareerData.url,
        })
        .then(() => getMyCareer())
        .catch((error) => {
            console.error('Error fetching addMyCareer:', error);
            })
    }
    //myCareer를 수정하는 기능
    const modifyMyCareer = () => {
        AxiosInstance
        .post('/mycareer/modify', {
            userId: '',
            resumeNum: resumeData.resumeNum,
            myCareerNum: resumeData.myCareerNum,
            job: myCareerData.job,
            introduction: myCareerData.introduction,
            coreCompetence1: myCareerData.coreCompetence1,
            coreCompetence2: myCareerData.coreCompetence2,
            coreCompetence3: myCareerData.coreCompetence3,
            coreCompetence4: myCareerData.coreCompetence4,
            coreCompetence5: myCareerData.coreCompetence5,
            url: myCareerData.url,
        })
        .then(() => getMyCareer())
        .catch((error) => {
            console.error('Error fetching modifyMyCareer:', error);
            })
    }
    //myCareer를 삭제하는 기능
    const deleteMyCareer = () => {
        AxiosInstance
        .delete('/mycareer/delete', {
            params: {resumeNum: resumeData.resumeNum,
                myCareerNum: resumeData.myCareerNum}
        })
        .then(() => getMyCareer())
        .catch((error) => {
            console.error('Error fetching deleteMyCareer:', error);
            })
    }

    useEffect(() => {
        if(isLogin){
            getResumeBasic();
            userInfo();
            getCareer(resumeData.resumeNum);
            getSkill();
            getMyCareer();
        }
    }, []);

    useEffect(() => {
        setCareerDynamicArray(resumeData.careerNums);
    }, [resumeData.careerNums]);


    const submitResume = () => {
        modifyResumeBasic();
        closeModal();
    }

    const selectButton = () => {
        movePage('/newForm');
        closeModal();
    }

    //InterviewList를 담는 Recoilstate
    const [interviewData, setInterviewData] = useRecoilState(interviewDataState);

    //InterviewList를 가져오기 위한 기능
    const getInterview = () => {
		AxiosInstance
		.get('/interview/list/resumeNum', {
            params: {resumeNum: resumeData.resumeNum}
        })
		.then((response) => {
			setInterviewData(response.data);
		})
		.then(() => {
			movePage('/interviewList');
		})
		.catch((error) => {
			console.log(error);
		});
	}

    const selectInterviewList = () => {
        getInterview();
    }

    const prevButton = () => {
        setCurrentPage(currentPage-1);
    }

    const nextButton = () => {
        setCurrentPage(currentPage+1);
    }

    if(!isLogin) {
        return <Navigate to="/signIn" replace={true} />
      } else{
	return (
		<div className={styles.ModalBackground}>
			<div className={styles.Modal}>
                { (isViewInterviewList == false || isView == false) && <header className={styles.ModalHeader}>
                    <Button onClick={() => closeModal()} className={styles.ColseBtn}>
                        종료
                    </Button>
                    <input id='resumeName' onChange={handleChange} value={basicData.resumeName} type='text' placeholder='이력서 이름'/>
                </header>}
                { (isViewInterviewList == true || isView == true) && <header className={styles.ModalHeader}>
                    <h4>{basicData.resumeName}</h4>
                </header>}
                { currentPage == 1 && <section className={styles.Section}>
                    <ResumeBasic isViewInterviewList={isViewInterviewList} isView={isView} basicData={basicData} setBasicData={setBasicData}/>
                </section>}
                { currentPage == 2 && <section className={styles.Section}>
                    <ResumeCareer isViewInterviewList={isViewInterviewList} isView={isView} deleteCareer={deleteCareer} modifyCareer={modifyCareer} addCareer={addCareer} careerDynamicArray={careerDynamicArray} setCareerDynamicArray={setCareerDynamicArray}/>
                </section>}
                { currentPage == 3 && <section className={styles.Section}>
                    <ResumeSkill isViewInterviewList={isViewInterviewList} isView={isView} deleteSkill={deleteSkill} addSkill={addSkill} skillDynamicArray={skillDynamicArray} setSkillDynamicArray={setSkillDynamicArray}/>
                </section>}
                { currentPage == 4 && <section className={styles.Section}>
                    <ResumeMyCareer isViewInterviewList={isViewInterviewList} isView={isView} deleteMyCareer={deleteMyCareer} modifyMyCareer={modifyMyCareer} addMyCareer={addMyCareer} myCareerData={myCareerData} setMyCareerData={setMyCareerData}/>
                </section>}
                <div className={styles.ExtraDiv}>
                    <Button
                        className={styles.Button}
                        onClick={() => prevButton()}
                        color="secondary"
                        disabled={prevDisabled}>
                        이전
                    </Button>
                    <StepDot maxPage={maxPage} currentPage={currentPage} size="lg" />
                    <Button
                        className={styles.Button}
                        onClick={() => nextButton()}
                        color="primary"
                        disabled={nextDisabled}>
                        다음
                    </Button>
                </div>
                { (isViewInterviewList == false && isView == false) && <section className={styles.ExtraSection}>
                <Button color='primary' onClick={() => submitResume()}>저장</Button>
                <Button onClick={() => deleteResume()} className={styles.ColseBtn} color='danger'>삭제</Button>
                </section>}
                { isView == true && <section className={styles.ExtraSection}>
                    <Button color='primary' onClick={() => selectButton()}>선택</Button>
                    <Button onClick={() => closeModal()} className={styles.ColseBtn} color='secondary'>취소</Button>
                </section>}
                { isViewInterviewList == true && <section className={styles.ExtraSection}>
                    <Button color='primary' onClick={() => selectInterviewList()}>AI인터뷰 기록보기</Button>
                    <Button onClick={() => closeModal()} className={styles.ColseBtn} color='secondary'>취소</Button>
                </section>}
            </div>
		</div>
	);
    }
};

export default Resume;
