import React from 'react';
import {Form} from 'react-bootstrap';

export const ReviewForm = () => {

    return(
        <div>
            <Form className="row">
                <div className="col-sm-6">
                    <Form.Group>
                        <Form.Label>Your name</Form.Label>
                        <Form.Control type="text" placeholder="Name..." />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Your email</Form.Label>
                        <Form.Control type="email" placeholder="Your Email..." />
                    </Form.Group>
                </div>
                <div className="col-sm-6">
                    <Form.Group>
                        <Form.Label>Book Title</Form.Label>
                        <Form.Control type="email" placeholder="Title..." />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label> Author of book</Form.Label>
                        <Form.Control type="email" placeholder="Author... (optional)" />
                    </Form.Group>
                </div>

                <div className="col-sm-12">
                    <Form.Group>
                        <Form.Label> Review</Form.Label>
                        <Form.Control as="textarea" rows={3}  />
                    </Form.Group>
                </div>

            </Form>
        </div>
    )
}