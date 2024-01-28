import React, { useRef, useState } from "react";

import styles from "./Interview.module.scss";

import { useNavigate } from "react-router-dom";

import cn from "classnames";

import { BiSolidCopy } from "react-icons/bi";
import { Button } from "reactstrap";

const Interview = ({interview, qualificationsNumber}) => {
    const navigate = useNavigate();
    const movePage = (url) => {
        navigate(url);
    }

    const maxRenderCount = 5;

    /* 데이터 필터 조건 */
    const filteredInterviews = interview.filter(item => {
        return item.qualificationsNumber === qualificationsNumber;
    }); 

    /* 질문의 블러처리 된 답변을 클릭 시 블러 On/Off하는 이벤트 */
    const [isBlurOn, setIsBlurOn] = React.useState(Array(maxRenderCount).fill(true));

    const answerClick = index => {
        setIsBlurOn(prevState => {
            const newState = [...prevState];
            newState[index] = !prevState[index];
            return newState;
        })
    }   

    /* 답변 내용 복사 이벤트 */
    const refs = useRef([]);

    const copyClick = (element) => {
        const text = element.innerText;

        navigator.clipboard.writeText(text)
        .then(() => {
            alert("복사되었습니다.");
        })
        .catch(() => {
            alert("복사에실패하였습니다.");
        })
    }

    return (
        
            <div className={styles.InterviewCheckContainer}>
                {filteredInterviews.map((item, index) => (
                    index < maxRenderCount && (
                    <section className={styles.Form} key={item.interivewNumber}>
                        <h4>{item.interviewQuestion}</h4>
                        <p id={'id' + item.interivewNumber} ref={preRef => refs.current[item.interivewNumber] = preRef} onClick={() => answerClick(index)} className={cn({[styles.Blur] : isBlurOn[index]}, [styles.Answer])}>
                            {item.interviewAnswer}
                        </p>
                        <BiSolidCopy onClick={() => copyClick(refs.current[item.interivewNumber])}/>
                    </section>
                    )
                ))}
            </div>
        
    )
}

export default Interview;