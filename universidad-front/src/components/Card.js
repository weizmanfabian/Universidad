import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React from 'react';
import { Link } from 'react-router-dom';

const Card = ({ title, description, url, classNameButton, textButton, classNameCard, icon }) => {
    return (
        <div className={classNameCard}> 
            <div className="card">
                <div className="card-body">
                    <FontAwesomeIcon icon={icon} className="me-2" />
                    <h5 className="card-title">{title}</h5> 
                    <p className="card-text">{description}</p> 
                    <Link to={url} className={classNameButton}>{textButton}</Link> 
                </div>
            </div>
        </div>
    );
};

export default Card;
