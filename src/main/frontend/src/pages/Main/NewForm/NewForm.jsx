import React, { useEffect } from 'react';
import axios from 'axios';
import AxiosInstance from '../../../api/AxiosInstance';
import { Button } from 'reactstrap';
import FormData from '../FormData/FormData';
import FormCheck from '../FormCheck/FormCheck';
import FormLoading from '../FormLoading/FormLoading';
import Interview from '../Interview/Interview';
import { Navigate, useLocation, useNavigate } from 'react-router-dom';
import StepDot from '../../../components/StepDot/StepDot';
import { JOB_DATAS, CAREER_DATAS, LEVEL_DATAS } from '../constants';
import styles from './NewForm.module.scss';
import { useRecoilState } from "recoil";
import { isLoginState } from '../../../recoil/RecoilState';
import GNB from '../../../components/GNB/GNB';

//드롭다운
const getDropdownItems = (datas, setter, key) => {
	return datas.map(({ title }) => ({
		title,
		onClick: () => setter((prev) => ({ ...prev, [key]: title })),
	}));
};

const maxPage = 4;

const NewForm = () => {

	const navigate = useNavigate();
  
    const movePage = (url) => {
      navigate(url)
    };

	const [isLogin, setIsLogin] = useRecoilState(isLoginState);

	const location = useLocation();
	const resumeNum = {...location.state}.resumeNum;
	/* 드롭다운 */
	const [form, setForm] = React.useState({
		job: '직무 선택',
		career: '연차 선택',
		level: '난이도 선택',
		requirements: '',
		qualificationsNumber: null,
	});
	const jobItems = getDropdownItems(JOB_DATAS, setForm, 'job');
	const careerItems = getDropdownItems(CAREER_DATAS, setForm, 'career');
	const levelItems = getDropdownItems(LEVEL_DATAS, setForm, 'level');

	/* props */
	const formData = {
		form: form,
		setForm: setForm,
		userId: localStorage.getItem('userId'),

		jobItems: jobItems,
		careerItems: careerItems,
		levelItems: levelItems,
	};

	const [interviewData, setInterviewData] = React.useState({
		interview: null,
		qualificationsNumber: form.qualificationsNumber,
	});


	/* 페이지 컨트롤 */
	const [currentPage, setCurrentPage] = React.useState(1);
	const prevDisabled = (currentPage == 1);
	const nextDisabled = (currentPage == maxPage ||
		form.job === '직무 선택' ||
		form.career === '연차 선택' ||
		form.level === '난이도 선택' ||
		form.requirements === '');

	const mappingPages = {
		1: <FormData {...formData} />,
		2: <FormCheck {...formData} />,
		3: <FormLoading />,
		4: <Interview {...interviewData}/>,

	};

	const RenderPage = mappingPages[currentPage];

	useEffect(() => {
		if (currentPage === 3) createQualifications();
	}, [currentPage]);

	/* 서버통신 */
	const createQualifications = () => {
		AxiosInstance
			.post('/qualification/add', {
				userId: '',

				job: form.job,
				career: form.career,
				level: form.level,
				requirements: form.requirements,
			})
			.then(() => {
				selectQualificationsNumber();
			});
	};

	const selectQualificationsNumber = () => {
		AxiosInstance
			.get(`/qualification/qualnum`, {
				userId: '',
			})
			.then((response) => {
				setInterviewData((prev) => ({
					...prev,
					qualificationsNumber: response.data.qualificationsNumber,
				}))
			})
			.then(() => {
				if(resumeNum != null){
					gptStartWithResume();
				} else{
					gptStart();
				}

			})
			.catch((error) => {
				console.log(error);
			});
	};

	const gptStartWithResume = () => {
		AxiosInstance
		.post('/chatgpt/needresume', {
			resumeNum: resumeNum,
		})
		.then(() => {
			getInterview();
		})
		.catch((error) => {
			console.log(error);
		});
	};

	const gptStart = () => {
		AxiosInstance
		.post('/chatgpt/noresume')
		.then(() => {
			getInterview();
		})
		.catch((error) => {
			console.log(error);
		});
	};
	
	const getInterview = () => {
		AxiosInstance
		.get('/interview/list', {userId: ''})
		.then((response) => {
			setInterviewData((prev) => ({
				...prev,
				interview: response.data,
			}));
		})
		.then(() => {
			setCurrentPage(4);
		})
		.catch((error) => {
			console.log(error);
		});
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
		<div className={styles.Main}>
			<GNB />
			<div className={styles.Container}>
			<section className={styles.Section}>{RenderPage}</section>
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
                        disabled={nextDisabled}
						>
                        다음
                    </Button>
                </div>
				<div className={styles.Section}>
			<Button
                        onClick={() => movePage('/selectResume')}
                        color="secondary">
							처음으로
					</Button>
			</div>
			</div>
			
		</div>
	);
					}
};

export default NewForm;
