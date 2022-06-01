import React from 'react';

const LoadingIndicator = () => {
    return (
    <div className="d-flex justify-content-center w-100">
        <div className="spinner-border" role="status">
            <span className="sr-only">Loading...</span>
        </div>
    </div>
    );
};

export default LoadingIndicator;